package me.jun.meeting.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
abstract public class PatternUtils {

    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$";
}
