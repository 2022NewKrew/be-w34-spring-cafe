package com.kakao.cafe.common.exception;

public class DeletionException extends BusinessException {
    public DeletionException() {
        super("해당 글을 지울 수 있는 권한이 없습니다.");
    }
}
