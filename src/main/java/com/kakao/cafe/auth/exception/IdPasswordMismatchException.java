package com.kakao.cafe.auth.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;

public class IdPasswordMismatchException extends BusinessException {

    public IdPasswordMismatchException(){
        super(ErrorType.ID_PASSWORD_MISMATCH);
    }
}
