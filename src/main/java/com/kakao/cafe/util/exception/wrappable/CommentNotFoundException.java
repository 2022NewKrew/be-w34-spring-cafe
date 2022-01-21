package com.kakao.cafe.util.exception.wrappable;

public class CommentNotFoundException extends CustomWrapperException {
    public CommentNotFoundException(Exception exception, long id) {
        super(exception, String.format("해당 id의 comment가 존재하지 않습니다! - id : ", id));
    }
}
