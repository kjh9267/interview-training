package me.jun.interviewtraining.common;

import me.jun.interviewtraining.common.error.ErrorResponse;
import me.jun.interviewtraining.support.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static me.jun.interviewtraining.common.error.ErrorCode.INVALID_CONTENT;
import static me.jun.interviewtraining.common.error.ErrorCode.UNEXPECTED_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidExceptionHandler() {
        ErrorResponse response = ErrorResponse.of(INVALID_CONTENT);
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> unexpectedExceptionHandler() {
        ErrorResponse response = ErrorResponse.of(UNEXPECTED_ERROR);
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }
}
