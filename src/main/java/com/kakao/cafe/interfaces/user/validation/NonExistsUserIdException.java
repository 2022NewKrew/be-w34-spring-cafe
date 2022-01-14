package com.kakao.cafe.interfaces.user.validation;

public class NonExistsUserIdException extends IllegalArgumentException {
    public NonExistsUserIdException() {
        super(UserErrorCode.NON_EXISTS_USER_ID.getMessage());
    }
}
