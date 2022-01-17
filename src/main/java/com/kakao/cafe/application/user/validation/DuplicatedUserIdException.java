package com.kakao.cafe.application.user.validation;

public class DuplicatedUserIdException extends IllegalArgumentException {
    public DuplicatedUserIdException() {
        super(UserErrorCode.DUPLICATED_USER_ID.getMessage());
    }
}
