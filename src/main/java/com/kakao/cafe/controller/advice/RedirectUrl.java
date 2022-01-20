package com.kakao.cafe.controller.advice;

import java.util.Arrays;

public enum RedirectUrl {
    ARTICLE_REDIRECT_URL("POST", "/articles", "redirect:/questions/form"),
    LOGIN_REDIRECT_URL("POST", "/login", "redirect:/login/form"),
    REGISTER_REDIRECT_URL("POST", "/users", "redirect:/users/form"),
    USER_UPDATE_REDIRECT_URL("POST", "/users/update", "redirect:/users/update"),
    ARTICLE_POST_PERMISSION_REDIRECT_URL("GET", "/articles/form", "redirect:/index"),
    ARTICLE_UPDATE_PERMISSION_REDIRECT_URL("GET", "/articles/update", "redirect:/articles"),
    ARTICLE_DELETE_PERMISSION_REDIRECT_URL("DELETE", "/articles/delete", "redirect:/articles"),
    ARTICLE_UPDATE_REDIRECT_URL("PUT", "/articles/update", "redirect:/articles/update"),
    REPLY_CREATE_REDIRECT_URL("POST", "/replies", "redirect:/articles"),
    REPLY_DELETE_REDIRECT_URL("DELETE", "/replies", "redirect:/articles"),
    DEFAULT_REDIRECT_URL("", "", "redirect:/");

    private final String method;
    private final String request;
    private final String redirect;

    RedirectUrl(String method, String request, String redirect) {
        this.method = method;
        this.request = request;
        this.redirect = redirect;
    }

    public static RedirectUrl getRedirectUrl(String method, String request) {
        return Arrays
                .stream(RedirectUrl.values())
                .filter(redirectUrl -> redirectUrl.method.equals(method)
                        && redirectUrl.request.equals(request))
                .findFirst()
                .orElse(RedirectUrl.DEFAULT_REDIRECT_URL);
    }

    public String getRedirect() {
        return redirect;
    }
}
