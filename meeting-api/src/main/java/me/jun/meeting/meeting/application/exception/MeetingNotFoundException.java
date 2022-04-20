package me.jun.meeting.meeting.application.exception;

import me.jun.meeting.support.BusinessException;

import static me.jun.meeting.common.error.ErrorCode.MEETING_NOT_FOUND;

public class MeetingNotFoundException extends BusinessException {

    public MeetingNotFoundException() {
        super(MEETING_NOT_FOUND);
    }
}
