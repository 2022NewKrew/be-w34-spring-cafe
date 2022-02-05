package com.kakao.cafe.domain;

import com.kakao.cafe.exception.UserException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

@Getter
public class Name {
    private static final String format = "^[A-Za-z가-힣]{1,50}";

    private final String name;

    public Name(String name) {
        validateNull(name);
        validateFormat(name);
        this.name = name;
    }

    private void validateNull(String name) {
        if (name == null) {
            throw new UserException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateFormat(String name) {
        if (!name.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_NAME);
        }
    }
}
