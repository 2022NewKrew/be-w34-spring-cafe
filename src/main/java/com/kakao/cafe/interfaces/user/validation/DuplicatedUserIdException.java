package com.kakao.cafe.interfaces.user.validation;

public class DuplicatedUserIdException extends IllegalArgumentException {
    public DuplicatedUserIdException() {
        super(UserErrorCode.DUPLICATED_USER_ID.getMessage());
    }
}
