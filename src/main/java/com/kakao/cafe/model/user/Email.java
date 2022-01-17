package com.kakao.cafe.model.user;

import com.kakao.cafe.utility.NullChecker;

import java.util.Objects;

public class Email {

    private static final int ALLOWED_LENGTH_EMAIL = 24;

    private final String value;

    public Email(String email) {
        checkEmail(email);

        this.value = email;
    }

    private void checkEmail(String email) {
        NullChecker.checkNotNull(email);

        if (email.length() > ALLOWED_LENGTH_EMAIL) {
            throw new IllegalArgumentException(
                    String.format("이메일의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_EMAIL));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
