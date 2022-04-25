package me.jun.interview.interview.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
public class Interviewer {

    private String email;

    public static Interviewer from(String email) {
        requireNonNull(email);
        return new Interviewer(email);
    }
}