package com.kakao.cafe.controller;

public class Constant {

    //articleController
    public static final int MAX_ARTICLES = 1;
    public static final int INDEX_OF_FIRST_ARTICLE = 1;

    //session UserId
    public static final String SESSION_USER_ID = "loginUserId";

    //ExceptionMessage
    public static final String PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER = "로그인한 사용자만 이용할 수 있습니다.";
    public static final String PERMISSION_EXCEPTION_MESSAGE_UPDATE_ONLY_WRITER = "게시글의 작성자만 글을 수정할 수 있습니다.";
    public static final String PERMISSION_EXCEPTION_MESSAGE_DELETE_ONLY_WRITER = "게시글의 작성자만 글을 삭제할 수 있습니다.";
    public static final String PERMISSION_EXCEPTION_OTHER_USER_REPLY_EXIST = "작성자의 댓글 이외의 댓글이 존재하여 삭제할 수 없습니다.";
    public static final String UNEXPECTED_EXCEPTION_MESSAGE = "예상하지 못한 예외가 발생했습니다.";
}
