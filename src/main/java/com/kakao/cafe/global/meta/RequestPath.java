package com.kakao.cafe.global.meta;

import org.springframework.http.HttpMethod;

import java.util.regex.Pattern;

public enum RequestPath {

    HOME(HttpMethod.GET, "/"),

    USER_JOIN(HttpMethod.POST, "/users"),
    USER_LIST(HttpMethod.GET, "/users"),
    USER_PROFILE(HttpMethod.GET, "/users/{userId}"),
    USER_UPDATE_FORM(HttpMethod.GET, "/users/{userId}/form"),
    USER_UPDATE(HttpMethod.PUT, "/users/{userId}"),
    USER_LOGIN(HttpMethod.POST, "/users/login"),

    USER_JOIN_FORM(HttpMethod.GET, "/users/joinForm"),
    USER_LOGIN_FORM(HttpMethod.GET, "/users/login/form"),
    USER_LOGIN_FORM_FAILED(HttpMethod.GET, "/users/login/form/failed"),

    ARTICLE_LIST(HttpMethod.GET, "/articles"),
    ARTICLE_REGISTER(HttpMethod.POST, "/articles"),
    ARTICLE_SHOW(HttpMethod.GET, "/articles/{id}"),

    ARTICLE_FROM(HttpMethod.GET, "/articles/form"),

    STATIC_FAVICON(HttpMethod.GET, "/favicon.ico"),
    STATIC_CSS(HttpMethod.GET, "/css/{}"),
    STATIC_FONTS(HttpMethod.GET, "/fonts/{}"),
    STATIC_IMAGES(HttpMethod.GET, "/images/{}"),
    STATIC_JS(HttpMethod.GET, "/js/{}");

    private HttpMethod method;
    private String path;
    private Pattern pathRegexPattern;

    RequestPath(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
        this.pathRegexPattern = Pattern.compile(getPathRegex());
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getPathRegex() {
        return path.replaceAll("\\{[\\w-]*}", "(.*)");
    }

    public boolean matchPath(String inputPath) {
        return pathRegexPattern.matcher(inputPath).matches();
    }

}
