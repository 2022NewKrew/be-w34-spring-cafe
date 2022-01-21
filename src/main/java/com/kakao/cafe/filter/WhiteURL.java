package com.kakao.cafe.filter;

import java.util.Arrays;

public enum WhiteURL {
    INDEX("GET", "/"),
    SIGNUP("POST", "/users"),
    SIGNUP_FORM("GET", "/users/form"),
    LOGIN_GET("GET", "/users/login"),
    LOGIN_POST("POST", "/users/login");

    private final String method;
    private final String url;

    WhiteURL(String method, String url) {
        this.method = method;
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public static boolean matchURL(String url, String method) {
        return Arrays.stream(WhiteURL.values()).filter(v -> url.equals(v.getUrl()) && method.equals(v.getMethod())).count() == 1;
    }
}
