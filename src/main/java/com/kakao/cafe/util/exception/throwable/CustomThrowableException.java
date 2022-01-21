package com.kakao.cafe.util.exception.throwable;

import com.kakao.cafe.util.exception.CustomException;

public class CustomThrowableException extends CustomException {

    public CustomThrowableException(String message) {
        super(message);
    }

    @Override
    public String getDetail() {
        return printStackTrace(this);
    }
}
