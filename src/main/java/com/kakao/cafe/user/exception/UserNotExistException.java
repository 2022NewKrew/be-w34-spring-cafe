package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class UserNotExistException extends BusinessException {

    public UserNotExistException(){
        super(ErrorType.USER_NOT_EXIST);
    }
}
