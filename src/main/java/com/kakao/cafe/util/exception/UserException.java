package com.kakao.cafe.util.exception;

import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

@Getter
public class UserException extends CustomException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
