package me.jun.interviewtraining.meeting.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jun.interviewtraining.meeting.application.exception.InvalidEmailException;
import me.jun.interviewtraining.meeting.domain.Interviewer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.jun.interviewtraining.support.PatternUtils.EMAIL_PATTERN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class RequestUser {

    private String email;

    public Interviewer toInterviewer() {
        return Interviewer.of(email);
    }

    public static RequestUser from(String email) {
        isValid(email);
        return new RequestUser(email);
    }

    private static void isValid(String email) {
        if (email == null) {
            throw new InvalidEmailException();
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new InvalidEmailException();
        }
    }
}
