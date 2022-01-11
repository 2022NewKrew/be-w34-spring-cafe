package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ErrorCode;
import com.kakao.cafe.utility.UserException;
import lombok.Getter;

@Getter
public class Password {
    private static final String format = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z0-9$@$!%*?&]{8,20}";

    private String password;

    public Password(String password) {
        validateFormat(password);
        this.password = password;
    }

    private void validateFormat(String password) {
        if(!password.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_PASSWORD);
        }
    }

    public String getPassword() {
        return password;
    }
}
