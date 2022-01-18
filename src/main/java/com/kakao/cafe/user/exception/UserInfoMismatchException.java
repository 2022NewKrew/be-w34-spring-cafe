package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class UserInfoMismatchException extends BusinessException {

    public UserInfoMismatchException(){
        super(ErrorType.USER_INFO_MISMATCH);
    }
}
