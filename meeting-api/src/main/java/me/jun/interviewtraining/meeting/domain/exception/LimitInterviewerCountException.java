package me.jun.interviewtraining.meeting.domain.exception;

import me.jun.interviewtraining.support.BusinessException;

import static me.jun.interviewtraining.common.error.ErrorCode.LIMIT_INTERVIEWER_COUNT;

public class LimitInterviewerCountException extends BusinessException {

    public LimitInterviewerCountException() {
        super(LIMIT_INTERVIEWER_COUNT);
    }
}
