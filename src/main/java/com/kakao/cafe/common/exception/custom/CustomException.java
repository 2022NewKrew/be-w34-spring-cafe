package com.kakao.cafe.common.exception.custom;

import com.kakao.cafe.common.exception.data.ErrorCode;

public interface CustomException {
    ErrorCode getErrorCode();
}
