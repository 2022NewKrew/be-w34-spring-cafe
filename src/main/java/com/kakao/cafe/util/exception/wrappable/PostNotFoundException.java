package com.kakao.cafe.util.exception.wrappable;

public class PostNotFoundException extends CustomWrapperException {
    public PostNotFoundException(Exception e, long id) {
        super(e, String.format("Post에 해당 id가 존재하지 않습니다!", id));
    }
}
