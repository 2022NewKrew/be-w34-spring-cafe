package com.kakao.cafe.util;

public enum ErrorMessage {
    USER_PROFILE_UPDATE_FORBIDDEN("자신의 계정만 수정할 수 있습니다."),
    NO_AUTH("로그인 후 이용해주세요."),
    LOGIN_FAILED("로그인에 실패하였습니다."),
    ARTICLE_FORBIDDEN("자신이 쓴 글에만 가능합니다."),
    ARTICLE_DELETE_NOT_MY_REPLY("자신이 쓰지 않은 댓글이 있는 경우 삭제할 수 없습니다.");


    private final String msg;

    private ErrorMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
