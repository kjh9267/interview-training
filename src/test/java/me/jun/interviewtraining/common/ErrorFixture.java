package me.jun.interviewtraining.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.jun.interviewtraining.common.error.ErrorResponse;

import static me.jun.interviewtraining.common.error.ErrorCode.*;

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