package com.kakao.cafe.url;

public class PostRedirect {
    // 앞쪽 경로
    public static final String REDIRECT_PREFIX = "redirect:";
    public static final String POST_BASE_URL = "/posts";

    // 뒤쪽 path
    public static final String POST_PATH_LIST = "/list";
    public static final String POST_PATH_WRITE_FAIL = "/write/fail";
//    public static final String POST_PATH_ = "/";

    // 전체 url
    public static final String POST_REDIRECT_LIST = REDIRECT_PREFIX+POST_BASE_URL+POST_PATH_LIST;
    public static final String POST_REDIRECT_WRITE_FAIL = REDIRECT_PREFIX+POST_BASE_URL+POST_PATH_WRITE_FAIL;
//    public static final String POST_REDIRECT_ = REDIRECT_PREFIX+POST_BASE_URL+;
}
