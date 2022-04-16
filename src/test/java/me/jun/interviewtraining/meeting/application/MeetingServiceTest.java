package me.jun.interviewtraining.meeting.application;

import me.jun.interviewtraining.meeting.application.dto.CreateMeetingRequest;
import me.jun.interviewtraining.meeting.application.dto.MeetingResponse;
import me.jun.interviewtraining.meeting.application.dto.RequestUser;
import me.jun.interviewtraining.meeting.domain.Interviewer;
import me.jun.interviewtraining.meeting.domain.Meeting;
import me.jun.interviewtraining.meeting.domain.repository.MeetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static me.jun.interviewtraining.meeting.MeetingFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("deprecation")
public class MeetingServiceTest {

    private MeetingService meetingService;

    @Mock
    private MeetingRepository meetingRepository;

    @BeforeEach
    void setUp() {
        meetingService = new MeetingService(meetingRepository);
    }

    @Test
    void createMeetingTest() {
        Meeting meeting = meeting();

        given(meetingRepository.save(any()))
                .willReturn(meeting);

        CreateMeetingRequest meetingRequest = createMeetingRequest();
        RequestUser requestUser = createRequestUser();

        MeetingResponse response = meetingService.createMeeting(meetingRequest, requestUser);

        assertThat(response)
                .isEqualToComparingFieldByField(meetingResponse());
    }

    @Test
    @Disabled
    void joinMeetingTest() {
        Meeting meeting = meeting();

        given(meetingRepository.findByUrl(MEETING_URL))
                .willReturn(Optional.of(meeting));

//        meetingService.joinMeeting()
    }

    @Test
    @Disabled
    void limitInterviewer_joinMeetingFailTest() {
        Set<Interviewer> interviewers = meeting().getInterviewers();

        interviewers.add(Interviewer.of("user1@email.com"));
        interviewers.add(Interviewer.of("user2@email.com"));


    }
}
