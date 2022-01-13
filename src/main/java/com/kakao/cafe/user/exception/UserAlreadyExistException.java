package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class UserAlreadyExistException extends BusinessException {

    public UserAlreadyExistException(){
        super(ErrorType.USER_ALREADY_EXIST);
    }
}
