package com.kakao.cafe.util.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(int id) {
        super(String.format("Post에 해당 id가 존재하지 않습니다!", id));
    }
}
