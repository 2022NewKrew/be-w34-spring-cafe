package com.kakao.cafe.common.meta;

public enum URLPath {

    HOME("/index"),
    INDEX("/"),
    SHOW_ARTICLE_FORM("/post/form"),
    SHOW_SIGN_UP_FORM("/user/form"),
    SHOW_ARTICLE("/post/show"),
    LOGIN_FORM("/user/login"),
    LOGIN_FAILED("/user/login_failed"),
    SHOW_USER_LIST("/user/list"),
    SHOW_USER_PROFILE("/user/profile"),
    SHOW_USERS("/users"),
    SHOW_USER_UPDATE_FORM("/user/updateForm"),
    SHOW_ERROR_404("/error/404");

    private final String path;

    URLPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getRedirectPath() {
        return "redirect:" + path;
    }
}
