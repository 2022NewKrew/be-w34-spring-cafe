package com.example.kakaocafe.core.meta;

import org.springframework.http.HttpMethod;

public enum URLPath {
    INDEX("/", HttpMethod.GET),
    SHOW_SIGN_UP_FORM("/sign-up", HttpMethod.GET),
    SHOW_SIGN_UP_SUCCESS("/sign-up-success", HttpMethod.GET),
    SHOW_LOGIN_FORM("/login", HttpMethod.GET),
    SHOW_USER_LIST("/users", HttpMethod.GET),
    SHOW_POST_FORM("/posts/form", HttpMethod.GET),
    SHOW_POST("/posts/", HttpMethod.GET),
    SHOW_USER_UPDATE_FORM("/users/updateForm", HttpMethod.GET),

    LOGIN("/users/login", HttpMethod.POST),
    LOGOUT("/users/logout", HttpMethod.GET),
    SIGN_UP("/users", HttpMethod.POST),
    UPDATE_USER("/users/updateForm", HttpMethod.PUT),
    GET_POST("/posts/", HttpMethod.GET),

    SHOW_ERROR_404("/error/404", HttpMethod.GET),

    CSS("/css/(.*)", HttpMethod.GET),
    IMAGE("/images/(.*)", HttpMethod.GET),
    META("/favicon.ico", HttpMethod.GET);

    private final String path;
    private final HttpMethod method;

    URLPath(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRedirectPath() {
        return "redirect:" + path;
    }

    public boolean isSameUrlAndMethod(String reqUrl, HttpMethod reqMethod) {
        return reqUrl.matches(path) && reqMethod.equals(method);
    }
}
