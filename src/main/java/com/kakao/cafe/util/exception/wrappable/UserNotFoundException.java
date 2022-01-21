package com.kakao.cafe.util.exception.wrappable;

public class UserNotFoundException extends CustomWrapperException {
    public UserNotFoundException(Exception e, String id) {
        super(e, String.format("User에 해당 ID가 존재하지 않습니다! : %s", id));

    }
}
