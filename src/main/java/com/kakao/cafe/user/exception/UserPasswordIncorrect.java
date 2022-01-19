package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;

public class UserPasswordIncorrect extends BusinessException {

    public UserPasswordIncorrect(){
        super(ErrorType.USER_PASSWORD_INCORRECT);
    }
}
