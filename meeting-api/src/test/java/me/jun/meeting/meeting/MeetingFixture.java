package me.jun.meeting.meeting;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.jun.meeting.meeting.application.dto.*;
import me.jun.meeting.meeting.domain.Interviewer;
import me.jun.meeting.meeting.domain.Meeting;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
abstract public class MeetingFixture {

    public static final Long MEETING_ID = 1L;

    public static final String MEETING_HOST_URL = "localhost:8081";

    public static final String CREATOR_EMAIL = "creator@email.com";

    public static final String MEETING_URL = MEETING_HOST_URL + "/" + CREATOR_EMAIL;

    public static final Interviewer CREATOR = Interviewer.of(CREATOR_EMAIL);

    public static final String INTERVIEWER_EMAIL = "testuser@email.com";

    public static final Interviewer INTERVIEWER = Interviewer.of(INTERVIEWER_EMAIL);

    public static final Long LIMIT_INTERVIEWER_COUNT = 2L;

    public static final String I_USER = "I-USER";

    public static Meeting meeting() {
        Meeting meeting = Meeting.of(LIMIT_INTERVIEWER_COUNT, CREATOR);

        meeting.toBuilder()
                .id(MEETING_ID);

        return meeting;
    }

    public static RetrieveMeetingRequest retrieveMeetingRequest() {
        return new RetrieveMeetingRequest(MEETING_URL);
    }

    public static CreateMeetingRequest createMeetingRequest() {
        return new CreateMeetingRequest(LIMIT_INTERVIEWER_COUNT);
    }

    public static RequestUser createRequestUser() {
        return new RequestUser(CREATOR_EMAIL);
    }

    public static MeetingResponse meetingResponse() {
        return MeetingResponse.from(meeting());
    }

    public static JoinMeetingRequest joinMeetingRequest() {
        return new JoinMeetingRequest(MEETING_URL);
    }

    public static RequestUser requestUser() {
        return new RequestUser(INTERVIEWER_EMAIL);
    }

    public static LeaveMeetingRequest leaveMeetingRequest() {
        return new LeaveMeetingRequest(MEETING_URL);
    }

    public static CreateMeetingRequest invalidCreateMeetingRequest() {
        return new CreateMeetingRequest(-1L);
    }
}
