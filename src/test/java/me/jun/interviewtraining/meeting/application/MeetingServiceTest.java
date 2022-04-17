package me.jun.interviewtraining.meeting.application;

import me.jun.interviewtraining.meeting.application.dto.CreateMeetingRequest;
import me.jun.interviewtraining.meeting.application.dto.MeetingResponse;
import me.jun.interviewtraining.meeting.application.dto.RequestUser;
import me.jun.interviewtraining.meeting.application.exception.MeetingNotFoundException;
import me.jun.interviewtraining.meeting.domain.Meeting;
import me.jun.interviewtraining.meeting.domain.repository.MeetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static me.jun.interviewtraining.meeting.MeetingFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void joinMeetingTest() {
        Meeting meeting = meeting();

        given(meetingRepository.findByUrl(MEETING_URL))
                .willReturn(Optional.of(meeting));

        assertThatCode(
                () -> meetingService.joinMeeting(joinMeetingRequest(), requestUser())
        )
                .doesNotThrowAnyException();
    }

    @Test
    void noMeeting_joinMeetingFailTest() {
        given(meetingRepository.findByUrl(MEETING_URL))
                .willThrow(MeetingNotFoundException.class);

        assertThrows(
                MeetingNotFoundException.class,
                () -> meetingService.joinMeeting(joinMeetingRequest(), requestUser())
        );
    }

    @Test
    void noMeeting_leaveMeetingFailTest() {
        given(meetingRepository.findByUrl(any()))
                .willThrow(MeetingNotFoundException.class);

        assertThrows(
                MeetingNotFoundException.class,
                () -> meetingService.leaveMeeting(leaveMeetingRequest(), requestUser())
        );
    }
}
