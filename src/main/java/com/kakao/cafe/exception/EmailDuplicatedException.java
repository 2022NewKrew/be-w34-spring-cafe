package com.kakao.cafe.exception;

public class EmailDuplicatedException extends CustomException {
    public EmailDuplicatedException() {
        super(ErrorCode.EMAIL_DUPLICATED);
    }
}
