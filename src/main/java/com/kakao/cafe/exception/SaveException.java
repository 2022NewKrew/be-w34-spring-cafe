package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class SaveException extends CustomException {
    public SaveException(ErrorCode errorCode) {
        super(errorCode);
    }
}
