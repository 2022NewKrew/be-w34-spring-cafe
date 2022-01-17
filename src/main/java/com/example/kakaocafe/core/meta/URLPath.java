package com.example.kakaocafe.core.meta;

public enum URLPath {
    INDEX("/"),
    SHOW_SIGN_UP_FORM("/sign-up"),
    SHOW_SIGN_UP_SUCCESS("/sign-up-success"),
    SHOW_LOGIN_FORM("/login"),
    SHOW_USER_LIST("/users"),
    SHOW_POST_FORM("/posts/form"),
    SHOW_POST("/posts/"),
    SHOW_USER_UPDATE_FORM("/users/updateForm"),
    LOGIN("/users/login"),
    LOGOUT("/users/logout"),
    SIGN_UP("/users"),
    UPDATE_USER("/users/updateForm"),
    GET_POST("/posts/"),
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
