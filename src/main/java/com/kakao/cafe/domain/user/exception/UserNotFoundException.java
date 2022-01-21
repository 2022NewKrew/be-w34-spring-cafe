package com.kakao.cafe.domain.user.exception;

public class UserNotFoundException extends IllegalArgumentException {

    public UserNotFoundException(Long id) {
        super(id + " 에 해당하는 사용자를 찾을 수 없습니다");
    }

    public UserNotFoundException(String email) {
        super(email + " 에 해당하는 사용자를 찾을 수 없습니다");
    }
}
