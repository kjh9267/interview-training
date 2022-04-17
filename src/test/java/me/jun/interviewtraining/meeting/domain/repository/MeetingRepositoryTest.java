package me.jun.interviewtraining.meeting.domain.repository;

import me.jun.interviewtraining.meeting.domain.Meeting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static me.jun.interviewtraining.meeting.MeetingFixture.MEETING_URL;
import static me.jun.interviewtraining.meeting.MeetingFixture.meeting;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class MeetingRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MeetingRepository meetingRepository;

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void createMeetingTest() {
        Meeting meeting = meeting();

        Meeting savedMeeting = meetingRepository.save(meeting);

        assertThat(savedMeeting)
                .isEqualTo(meeting);
    }

    @Test
    void createMeetingFailTest() {
        Meeting meeting = meeting();

        Meeting savedMeeting = meetingRepository.save(meeting);

        System.out.println(savedMeeting);

        Meeting meeting2 = meeting().toBuilder()
                .id(2L)
                .build();

        assertThrows(
                DataIntegrityViolationException.class,
                () -> meetingRepository.save(meeting2)
        );
    }

    @Test
    void findByUrlTest() {
        Meeting savedMeeting = meetingRepository.save(meeting());

        Meeting meeting = meetingRepository.findByUrl(MEETING_URL)
                .get();

        assertThat(meeting)
                .isEqualTo(savedMeeting);
    }
}
