package com.kakao.cafe.user.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private final String value;

    public Email(String value) {
        validateEmailFormat(value);
        this.value = value;
    }

    private void validateEmailFormat(String email) {
        final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong email format");
        }
    }

    public String getValue() {
        return value;
    }
}
