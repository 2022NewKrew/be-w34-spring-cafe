package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

@Getter
public class UserException extends CustomException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
