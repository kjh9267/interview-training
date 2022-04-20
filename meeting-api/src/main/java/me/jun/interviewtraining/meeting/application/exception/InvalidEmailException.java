package me.jun.interviewtraining.meeting.application.exception;

import me.jun.interviewtraining.common.error.ErrorCode;
import me.jun.interviewtraining.support.BusinessException;

public class InvalidEmailException extends BusinessException {

    public InvalidEmailException() {
        super(ErrorCode.INVALID_EMAIL);
    }
}
