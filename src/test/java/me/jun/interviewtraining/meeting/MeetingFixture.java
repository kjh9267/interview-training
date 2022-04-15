package me.jun.interviewtraining.meeting;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.jun.interviewtraining.meeting.domain.Interviewer;
import me.jun.interviewtraining.meeting.domain.Meeting;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
abstract public class MeetingFixture {

    public static final Long MEETING_ID = 1L;

    public static final String MEETING_URL = "localhost:8080";

    public static final String CREATOR_EMAIL = "creator@email.com";

    public static final Interviewer CREATOR = Interviewer.of(CREATOR_EMAIL);

    public static final String INTERVIEWER_EMAIL = "testuser@email.com";

    public static final Interviewer INTERVIEWER = Interviewer.of(INTERVIEWER_EMAIL);

    public static final Long LIMIT_INTERVIEWER_COUNT = 2L;

    public static Meeting meeting() {
        Meeting meeting = Meeting.of(LIMIT_INTERVIEWER_COUNT, CREATOR);

        meeting.toBuilder()
                .id(MEETING_ID);

        return meeting;
    }
}
