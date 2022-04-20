package me.jun.interviewtraining.support;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jun.interviewtraining.common.error.ErrorCode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;
}
