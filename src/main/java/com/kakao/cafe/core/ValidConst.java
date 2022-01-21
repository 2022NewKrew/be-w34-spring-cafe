package com.kakao.cafe.core;

public class ValidConst {
    public static final int MAX_USER_ID_LEN = 15;
    public static final int MAX_USER_EMAIL_LEN = 40;
    public static final int MAX_USER_NAME_LEN = 20;
    public static final int MAX_USER_PASSWORD_LEN = 30;

    public static final int MAX_ARTICLE_TITLE_LEN = 30;
    public static final int MAX_ARTICLE_CONTENT_LEN = 255;

    public static final String USER_ID_MESSAGE = "id의 최대 길이는" + ValidConst.MAX_USER_ID_LEN + "입니다.";
    public static final String USER_EMAIL_MESSAGE = "email의 최대 길이는 " + ValidConst.MAX_USER_EMAIL_LEN + "입니다.";
    public static final String USER_NAME_MESSAGE = "유저 이름의 최대 길이는 " + ValidConst.MAX_USER_NAME_LEN + "입니다.";
    public static final String USER_PASSWORD_MESSAGE = "비밀번호의 최대 길이는 " + ValidConst.MAX_USER_PASSWORD_LEN + "입니다.";

    public static final String ARTICLE_TITLE_MESSAGE = "타이틀은 " + ValidConst.MAX_ARTICLE_TITLE_LEN + " 글자 이하 입니다.";
    public static final String ARTICLE_CONTENT_MESSAGE = "본문 내용 " + ValidConst.MAX_ARTICLE_CONTENT_LEN + " 글자 이하 입니다.";
}
