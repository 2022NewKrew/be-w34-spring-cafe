package com.kakao.cafe.domain;

import com.kakao.cafe.exception.UserException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Password password = (Password) object;
        return Objects.equals(this.password, password.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
