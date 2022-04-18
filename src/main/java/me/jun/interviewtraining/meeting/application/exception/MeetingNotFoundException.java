package me.jun.interviewtraining.meeting.application.exception;

import me.jun.interviewtraining.support.BusinessException;

import static me.jun.interviewtraining.common.ErrorCode.MEETING_NOT_FOUND;

public class MeetingNotFoundException extends BusinessException {

    public MeetingNotFoundException() {
        super(MEETING_NOT_FOUND);
    }
}
