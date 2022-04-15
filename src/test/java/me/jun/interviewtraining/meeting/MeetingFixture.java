package me.jun.interviewtraining.meeting;

import me.jun.interviewtraining.meeting.domain.Meeting;

abstract public class MeetingFixture {

    public static final Long MEETING_ID = 1L;

    public static final String MEETING_URL = "test-url.com/1";

    public static final Long INTERVIEWER_COUNT = 1L;

    public static final String INTERVIEW_EMAIL = "testuser@email.com";

    public static final Long LIMIT_INTERVIEWER_COUNT = 2L;

    public static Meeting meeting() {
        return Meeting.builder()
                .id(MEETING_ID)
                .url(MEETING_URL)
                .interviewerCount(INTERVIEWER_COUNT)
                .limitInterviewerCount(LIMIT_INTERVIEWER_COUNT)
                .build();
    }
}
