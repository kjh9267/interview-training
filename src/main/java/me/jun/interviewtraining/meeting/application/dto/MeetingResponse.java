package me.jun.interviewtraining.meeting.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.jun.interviewtraining.meeting.domain.Meeting;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MeetingResponse {

    private String url;

    public static MeetingResponse from(Meeting meeting) {
        return MeetingResponse.builder()
                .url(meeting.getUrl())
                .build();
    }
}
