package com.kakao.cafe.service;

public class Constant {

    //article
    public static final String ARTICLE_IS_NOT_EXIST = "찾는 게시물이 없습니다.";
    public static final String DELETE_ARTICLE_ONLY_WRITER = "작성자만 게시글을 삭제할 수 있습니다.";
    public static final String OTHER_REPLY_EXIST = "작성자 외의 댓글이 존재하여서 게시글을 삭제할 수 없습니다.";

    //reply
    public static final String DELETE_REPLY_ONLY_WRITER = "본인의 댓글만 삭제할 수 있습니다.";
    public static final String REPLY_IS_NOT_EXIST = "찾는 댓글이 존재하지 않습니다.";

    //user
    public static final String USER_IS_NOT_EXIST = "찾는 사용자가 존재하지 않습니다.";
    public static final String ALREADY_EXIST_USER = "이미 존재하는 사용자입니다.";
    public static final String NOT_MATCH_PASSWORD = "비밀번호가 일치하지 않습니다.";
}
