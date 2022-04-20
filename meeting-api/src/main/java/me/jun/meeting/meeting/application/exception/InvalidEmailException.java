package me.jun.meeting.meeting.application.exception;

import me.jun.meeting.common.error.ErrorCode;
import me.jun.meeting.support.BusinessException;

public class InvalidEmailException extends BusinessException {

    public InvalidEmailException() {
        super(ErrorCode.INVALID_EMAIL);
    }
}
