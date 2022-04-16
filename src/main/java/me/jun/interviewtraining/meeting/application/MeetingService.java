package me.jun.interviewtraining.meeting.application;

import lombok.RequiredArgsConstructor;
import me.jun.interviewtraining.meeting.application.dto.CreateMeetingRequest;
import me.jun.interviewtraining.meeting.application.dto.JoinMeetingRequest;
import me.jun.interviewtraining.meeting.application.dto.MeetingResponse;
import me.jun.interviewtraining.meeting.application.dto.RequestUser;
import me.jun.interviewtraining.meeting.domain.Interviewer;
import me.jun.interviewtraining.meeting.domain.Meeting;
import me.jun.interviewtraining.meeting.domain.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingResponse createMeeting(CreateMeetingRequest request, RequestUser user) {
        Long limitInterviewerCount = request.getLimitInterviewerCount();
        Interviewer interviewer = user.toInterviewer();

        Meeting meeting = Meeting.of(limitInterviewerCount, interviewer);

        Meeting savedMeeting = meetingRepository.save(meeting);

        return MeetingResponse.from(savedMeeting);
    }

    public void joinMeeting(JoinMeetingRequest request, RequestUser user) {
        String url = request.getUrl();
        String email = user.getEmail();

//        meeting
    }
}
