package me.jun.interviewtraining.meeting.domain;

import lombok.*;

import javax.persistence.Embeddable;

import static java.util.Objects.requireNonNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "email")
@ToString
public class Interviewer {

    private String email;

    public static Interviewer of(String email) {
        requireNonNull(email);
        return new Interviewer(email);
    }
}
