package com.kakao.cafe.exception;

public class ErrorMessages {

    public static final String WRONG_PASSWORD_FORMAT;
    public static final String WRONG_EMAIL_FORMAT;
    public static final String WRONG_NAME_FORMAT;
    public static final String DUPLICATED_USERID;
    public static final String NO_SUCH_MEMBER;
    public static final String NO_SUCH_ARTICLE;

    static {
        WRONG_PASSWORD_FORMAT = "올바르지 않은 형식의 비밀번호 입니다.";
        WRONG_EMAIL_FORMAT = "올바르지 않은 형식의 이메일 입니다.";
        WRONG_NAME_FORMAT = "올바르지 않은 형식의 이름 입니다.";
        DUPLICATED_USERID = "중복된 userId 입니다.";
        NO_SUCH_MEMBER = "조건에 해당하는 회원이 없습니다.";
        NO_SUCH_ARTICLE = "조건에 해당하는 게시글이 없습니다.";
    }
}
