package com.kakao.cafe.domain.member;

import com.kakao.cafe.exception.ErrorMessages;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Email {

    private final String email;
    private final static String EMAIL_PATTERN = ".*@.*\\..*";

    public Email(String email) {
        if (!email.matches(EMAIL_PATTERN))
            throw new IllegalArgumentException(ErrorMessages.WRONG_EMAIL_FORMAT);
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
