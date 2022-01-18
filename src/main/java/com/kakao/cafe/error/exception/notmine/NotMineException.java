package com.kakao.cafe.error.exception.notmine;

import com.kakao.cafe.error.exception.BusinessLogicException;

public class NotMineException extends BusinessLogicException {
    public NotMineException(String msg) {
        super(msg);
    }
}
