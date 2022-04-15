package me.jun.interviewtraining.meeting.domain;

import org.junit.jupiter.api.Test;

import static me.jun.interviewtraining.meeting.MeetingFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("deprecation")
public class MeetingTest {

    @Test
    void defaultConstructorTest() {
        assertThat(new Meeting())
                .isInstanceOf(Meeting.class);
    }

    @Test
    void createMeetingTest() {
        Meeting expected = meeting();

        Meeting meeting = Meeting.builder()
                .id(1L)
                .url("test-url.com/1")
                .interviewerCount(1L)
                .limitInterviewerCount(2L)
                .build();

        assertThat(meeting)
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void canJoinTest() {
        Meeting meeting = meeting();

        assertThat(meeting.canJoin(INTERVIEW_EMAIL))
                .isTrue();
    }

    @Test
    void canJoinFailTest() {
        Meeting meeting = meeting()
                .toBuilder()
                .limitInterviewerCount(1L)
                .build();

        assertThat(meeting.canJoin(INTERVIEW_EMAIL))
                .isFalse();
    }
}
