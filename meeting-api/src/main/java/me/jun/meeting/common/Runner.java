package me.jun.meeting.common;

import lombok.RequiredArgsConstructor;
import me.jun.meeting.meeting.domain.Interviewer;
import me.jun.meeting.meeting.domain.Meeting;
import me.jun.meeting.meeting.domain.repository.MeetingRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

    private final MeetingRepository meetingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Meeting meeting = Meeting.of(2L, Interviewer.of("creator@email.com"));

        meetingRepository.save(meeting);
    }
}
