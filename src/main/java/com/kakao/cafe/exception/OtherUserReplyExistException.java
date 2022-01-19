package com.kakao.cafe.exception;

public class OtherUserReplyExistException extends RuntimeException {
    public OtherUserReplyExistException(String message) {
        super(message);
    }
}
