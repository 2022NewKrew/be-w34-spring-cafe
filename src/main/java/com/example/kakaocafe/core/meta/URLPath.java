package com.example.kakaocafe.core.meta;

public enum URLPath {
    INDEX("/"),
    SHOW_SIGN_UP_FROM("/sign-up"),
    SHOW_LOGIN_FROM("/login"),
    SHOW_LOGIN_FAILED("/login_failed"),
    SHOW_USER_LIST("/users"),
    SHOW_POST_FORM("/post/form"),
    SHOW_USER_UPDATE_FORM("/users/updateForm"),
    LOGIN("/users/login"),
    LOGOUT("/users/logout"),
    SIGN_UP("/users"),
    UPDATE_USER("/users/updateForm");

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
