package me.jun.interviewtraining.meeting.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jun.interviewtraining.meeting.domain.Interviewer;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class RequestUser {

    @NotBlank
    private String email;

    public Interviewer toInterviewer() {
        return Interviewer.of(email);
    }
}
