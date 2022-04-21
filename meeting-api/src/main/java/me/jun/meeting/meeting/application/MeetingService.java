package me.jun.meeting.meeting.application;

import lombok.RequiredArgsConstructor;
import me.jun.meeting.meeting.application.dto.*;
import me.jun.meeting.meeting.application.exception.MeetingNotFoundException;
import me.jun.meeting.meeting.domain.Interviewer;
import me.jun.meeting.meeting.domain.Meeting;
import me.jun.meeting.meeting.domain.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Transactional(readOnly = true)
    public MeetingResponse retrieveMeeting(RetrieveMeetingRequest request) {
        String url = request.getUrl();

        Meeting meeting = meetingRepository.findByUrl(url)
                .orElseThrow(MeetingNotFoundException::new);

        return MeetingResponse.from(meeting);
    }

    public MeetingResponse createMeeting(CreateMeetingRequest request, RequestUser user) {
        Long limitInterviewerCount = request.getLimitInterviewerCount();
        Interviewer interviewer = user.toInterviewer();

        Meeting meeting = Meeting.of(limitInterviewerCount, interviewer);

        Meeting savedMeeting = meetingRepository.save(meeting);

        return MeetingResponse.from(savedMeeting);
    }

    public MeetingResponse joinMeeting(JoinMeetingRequest request, RequestUser user) {
        String url = request.getUrl();
        Interviewer interviewer = user.toInterviewer();

        Meeting meeting = meetingRepository.findByUrl(url)
                .orElseThrow(MeetingNotFoundException::new);

        meeting.join(interviewer);

        return MeetingResponse.from(meeting);
    }

    public void leaveMeeting(LeaveMeetingRequest request, RequestUser user) {
        String url = request.getUrl();
        Interviewer interviewer = user.toInterviewer();

        Meeting meeting = meetingRepository.findByUrl(url)
                .orElseThrow(MeetingNotFoundException::new);

        meeting.leave(interviewer);
    }
}
