package me.jun.interviewtraining.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static me.jun.interviewtraining.common.ErrorCode.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
abstract public class ErrorFixture {

    public static ErrorResponse meetingNotFoundErrorResponse() {
        return ErrorResponse.of(MEETING_NOT_FOUND);
    }

    public static ErrorResponse limitInterviewerCountErrorResponse() {
        return ErrorResponse.of(LIMIT_INTERVIEWER_COUNT);
    }

    public static ErrorResponse invalidContentErrorResponse() {
        return ErrorResponse.of(INVALID_CONTENT);
    }
}