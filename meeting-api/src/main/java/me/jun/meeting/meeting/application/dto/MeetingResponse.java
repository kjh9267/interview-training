package me.jun.meeting.meeting.application.dto;

import lombok.*;
import me.jun.meeting.meeting.domain.Meeting;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class MeetingResponse {

    private String url;

    public static MeetingResponse from(Meeting meeting) {
        return MeetingResponse.builder()
                .url(meeting.getUrl())
                .build();
    }
}
