package com.kakao.cafe.exception;

public class ErrorMessages {

    public static final String WRONG_PASSWORD_FORMAT;
    public static final String WRONG_EMAIL_FORMAT;
    public static final String WRONG_NAME_FORMAT;
    public static final String DUPLICATED_USERID;
    public static final String NO_SUCH_MEMBER;
    public static final String NO_SUCH_ARTICLE;
    public static final String LOGIN_FAILED;
    public static final String NOT_AUTHENTICATED_USER;
    public static final String NOT_AUTHORIZED_USER;
    public static final String NOT_ENOUGH_FIELD;

    static {
        WRONG_PASSWORD_FORMAT = "올바르지 않은 형식의 비밀번호 입니다.";
        WRONG_EMAIL_FORMAT = "올바르지 않은 형식의 이메일 입니다.";
        WRONG_NAME_FORMAT = "올바르지 않은 형식의 이름 입니다.";
        DUPLICATED_USERID = "중복된 userId 입니다.";
        NO_SUCH_MEMBER = "조건에 해당하는 회원이 없습니다.";
        NO_SUCH_ARTICLE = "조건에 해당하는 게시글이 없습니다.";
        LOGIN_FAILED = "아이디 혹은 비밀번호가 일치하지 않습니다.";
        NOT_AUTHENTICATED_USER = "로그인 후에 이용 가능합니다.";
        NOT_AUTHORIZED_USER = "권한이 없습니다.";
        NOT_ENOUGH_FIELD = "입력 항목이 부족합니다.";
    }
}
