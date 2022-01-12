package com.example.kakaocafe.core.meta;

public enum ViewPath {
    INDEX("index"),
    SHOW_USER_LIST("user/list"),
    LOGIN("user/login"),
    LOGIN_FAILED("user/login_failed"),
    SIGN_UP("user/sign_up"),
    SIGN_UP_FAILED("user/sign_up_failed"),
    SIGN_UP_SUCCESS("user/sign_up_success"),
    UPDATE_USER_FROM("user/update_form"),
    WRITE_POST("post/form"),
    SHOW_POST("post/show");

    private final String path;

    ViewPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
