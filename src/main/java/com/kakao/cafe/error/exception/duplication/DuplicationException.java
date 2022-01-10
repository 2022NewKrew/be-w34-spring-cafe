package com.kakao.cafe.error.exception.duplication;

import com.kakao.cafe.error.exception.BusinessLogicException;

public class DuplicationException extends BusinessLogicException {
    public DuplicationException(String msg) {
        super(msg);
    }
}
