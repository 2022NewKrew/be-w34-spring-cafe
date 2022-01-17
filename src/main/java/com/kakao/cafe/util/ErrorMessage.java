package com.kakao.cafe.util;

public enum ErrorMessage {
    UPDATE_FORBIDDEN("자신의 계정만 수정할 수 있습니다."),
    UPDATE_NON_LOGIN("로그인 후 이용해주세요."),
    LOGIN_FAILED("로그인에 실패하였습니다.");


    private String msg;
    private ErrorMessage(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
