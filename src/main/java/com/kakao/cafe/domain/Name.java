package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ErrorCode;
import com.kakao.cafe.utility.UserException;
import lombok.Getter;

@Getter
public class Name {
    private static final String format = "^[A-Za-z가-힣]{1,50}";

    private String name;

    public Name(String name) {
        validateFormat(name);
        this.name = name;
    }

    private void validateFormat(String name) {
        if(!name.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_NAME);
        }
    }
}
