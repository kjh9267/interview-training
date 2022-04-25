package me.jun.meeting.meeting.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class RetrieveMeetingRequest {

    @NotBlank
    private String url;

    public static RetrieveMeetingRequest from(String url) {
        return new RetrieveMeetingRequest(url);
    }
}
