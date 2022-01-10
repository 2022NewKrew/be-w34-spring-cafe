package com.kakao.cafe.error.exception.nonexist;

import com.kakao.cafe.error.exception.BusinessLogicException;

public class NotFoundedException extends BusinessLogicException {
    public NotFoundedException(String msg) {
        super(msg);
    }
}
