package com.kakao.cafe.controller;

public class Constant {

    //articleController
    public static final int MAX_ARTICLES = 1;
    public static final int PAGE_LIMIT = 5;

    //session UserId
    public static final String SESSION_USER_ID = "loginUserId";

    //ExceptionMessage
    public static final String PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER = "로그인한 사용자만 이용할 수 있습니다.";
    public static final String PERMISSION_EXCEPTION_MESSAGE_UPDATE_ONLY_WRITER = "게시글의 작성자만 글을 수정할 수 있습니다.";
    public static final String UNEXPECTED_EXCEPTION_MESSAGE = "예상하지 못한 예외가 발생했습니다.";
}
