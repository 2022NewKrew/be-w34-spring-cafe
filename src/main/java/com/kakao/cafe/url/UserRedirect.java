package com.kakao.cafe.url;

public class UserRedirect {
    // 앞쪽 경로
    public static final String REDIRECT_PREFIX = "redirect:";
    public static final String USER_BASE_URL = "/users";

    // 뒤쪽 path
    public static final String USER_PATH_LIST = "/list";
    public static final String USER_PATH_SIGN_UP_FAIL = "/sign-up/fail";
//    public static final String USER_PATH_ = "/";

    // 전체 url
    public static final String USER_REDIRECT_LIST = REDIRECT_PREFIX+USER_BASE_URL+USER_PATH_LIST;
    public static final String USER_REDIRECT_SIGN_UP_FAIL = REDIRECT_PREFIX+USER_BASE_URL+USER_PATH_SIGN_UP_FAIL;
//    public static final String USER_REDIRECT_ = REDIRECT_PREFIX+USER_BASE_URL+;
}
