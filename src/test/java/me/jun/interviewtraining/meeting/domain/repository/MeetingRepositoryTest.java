package me.jun.interviewtraining.meeting.domain.repository;

import me.jun.interviewtraining.meeting.domain.Meeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static me.jun.interviewtraining.meeting.MeetingFixture.meeting;

@DataJpaTest
public class MeetingRepositoryTest {

    @Autowired
    private MeetingRepository meetingRepository;

    @Test
    @Rollback(value = false)
    void createMeetingTest() {
        Meeting meeting = meeting();

        Meeting savedMeeting = meetingRepository.save(meeting);

        savedMeeting.getInterviewers()
                .stream()
                .forEach(System.out::println);
    }
}
