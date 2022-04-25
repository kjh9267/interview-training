package me.jun.meeting.meeting.domain;

import org.junit.jupiter.api.Test;

import static me.jun.meeting.meeting.MeetingFixture.INTERVIEWER_EMAIL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InterviewerTest {

    @Test
    void defaultConstructorTest() {
        Interviewer interviewer = new Interviewer();

        assertThat(interviewer)
                .isInstanceOf(Interviewer.class);
    }

    @Test
    void ofTest() {
        Interviewer interviewer = Interviewer.of(INTERVIEWER_EMAIL);

        assertThat(interviewer.getEmail())
                .isEqualTo("testuser@email.com");
    }

    @Test
    void null_ofFailTest() {
        assertThrows(
                NullPointerException.class,
                () -> Interviewer.of(null)
        );
    }
}