package com.kakao.cafe.application.user.validation;

public class NonExistsUserIdException extends IllegalArgumentException {
    public NonExistsUserIdException() {
        super(UserErrorCode.NON_EXISTS_USER_ID.getMessage());
    }
}
