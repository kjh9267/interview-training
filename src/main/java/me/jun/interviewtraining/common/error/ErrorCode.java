package me.jun.interviewtraining.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    MEETING_NOT_FOUND(NOT_FOUND, "meeting not found"),

    LIMIT_INTERVIEWER_COUNT(BAD_REQUEST, "limit interviewer count"),

    INVALID_CONTENT(BAD_REQUEST, "check your content of request body"),

    INVALID_EMAIL(BAD_REQUEST, "email is empty or pattern not matching"),

    UNEXPECTED_ERROR(INTERNAL_SERVER_ERROR, "sorry");

    private HttpStatus status;

    private String message;
}
