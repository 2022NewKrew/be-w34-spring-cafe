package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ErrorCode;
import com.kakao.cafe.utility.UserException;
import lombok.Getter;

@Getter
public class Email {
    private static final String format = "^[A-Za-z20-9._-]+@[a-zA-z0-9]+\\.[a-z]+$";

    private String email;

    public Email(String email) {
        validateFormat(email);
        this.email = email;
    }

    private void validateFormat(String email) {
        if(!email.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_EMAIL);
        }
    }

    public String getEmail() {
        return email;
    }
}
