package me.jun.meeting.meeting.domain.exception;

import me.jun.meeting.support.BusinessException;

import static me.jun.meeting.common.error.ErrorCode.LIMIT_INTERVIEWER_COUNT;

public class LimitInterviewerCountException extends BusinessException {

    public LimitInterviewerCountException() {
        super(LIMIT_INTERVIEWER_COUNT);
    }
}
