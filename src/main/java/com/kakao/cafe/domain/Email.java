package com.kakao.cafe.domain;

import com.kakao.cafe.exception.UserException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

@Getter
public class Email {
    private static final String format = "^[A-Za-z20-9._-]+@[a-zA-z0-9]+\\.[a-z]+$";

    private final String email;

    public Email(String email) {
        validateNull(email);
        validateFormat(email);
        this.email = email;
    }

    private void validateNull(String email) {
        if (email == null) {
            throw new UserException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateFormat(String email) {
        if (!email.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_EMAIL);
        }
    }

    public String getEmail() {
        return email;
    }
}
