package com.kakao.cafe.exception;

public class ServerError extends CustomException {
    public ServerError() {
        super(ErrorCode.SERVER_ERROR);
    }
}
