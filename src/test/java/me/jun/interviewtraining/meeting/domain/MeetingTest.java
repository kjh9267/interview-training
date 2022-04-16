package me.jun.interviewtraining.meeting.domain;

import me.jun.interviewtraining.meeting.domain.exception.LimitInterviewerCountException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static me.jun.interviewtraining.meeting.MeetingFixture.INTERVIEWER;
import static me.jun.interviewtraining.meeting.MeetingFixture.meeting;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Interviewer creator = Interviewer.of("creator@email.com");
        Meeting meeting = Meeting.of(2L, creator);

        assertThat(meeting)
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void canJoinTest() {
        Meeting meeting = meeting();

        assertThat(meeting.canJoin())
                .isTrue();
    }

    @Test
    void canJoinFailTest() {
        Meeting meeting = meeting()
                .toBuilder()
                .limitInterviewerCount(1L)
                .build();

        assertThat(meeting.canJoin())
                .isFalse();
    }

    @Test
    void joinTest() {
        Meeting meeting = meeting();

        meeting.join(INTERVIEWER);

        assertThat(
                meeting.getInterviewers()
                        .contains(INTERVIEWER)
        )
                .isTrue();
    }

    @Test
    void limitInterviewerCount_joinFailTest() {
        Meeting meeting = meeting();

        Set<Interviewer> interviewers = meeting.getInterviewers();
        interviewers.add(Interviewer.of("user1@email.com"));
        interviewers.add(Interviewer.of("user2@email.com"));

        assertThrows(
                LimitInterviewerCountException.class,
                () -> meeting.join(INTERVIEWER)
        );
    }
}
