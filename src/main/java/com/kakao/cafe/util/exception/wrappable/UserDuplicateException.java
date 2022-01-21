package com.kakao.cafe.util.exception.wrappable;

public class UserDuplicateException extends CustomWrapperException {
    public UserDuplicateException(Exception e, String id) {
        super(e, String.format("해당 ID의 User가 이미 존재합니다! : %s", id));
    }
}
