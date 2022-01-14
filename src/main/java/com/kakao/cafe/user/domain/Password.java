package com.kakao.cafe.user.domain;

import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.UserException;
import lombok.Getter;

@Getter
public class Password {
    private static final String format = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[#$@!%*?&])[A-Za-z0-9#$@!%*?&]{8,20}";

    private final String password;

    public Password(String password) {
        validateNull(password);
        validateFormat(password);
        this.password = password;
    }

    private void validateNull(String password) {
        if (password == null) {
            throw new UserException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateFormat(String password) {
        if (!password.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_PASSWORD);
        }
    }

    public String getPassword() {
        return password;
    }
}
