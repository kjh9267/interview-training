package me.jun.meeting.meeting.application.dto;

import lombok.*;
import me.jun.meeting.meeting.domain.Interviewer;
import me.jun.meeting.meeting.domain.Meeting;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class MeetingResponse {

    private String url;

    private Set<Interviewer> interviewers;

    public static MeetingResponse from(Meeting meeting) {
        return MeetingResponse.builder()
                .url(meeting.getUrl())
                .interviewers(meeting.getInterviewers())
                .build();
    }
}
