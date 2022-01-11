package com.kakao.cafe.data;

public enum UrlData {
    USER_HOST_URL("/users"),
    POST_HOST_URL("/posts"),
    ;

    String url;
    UrlData(String url) {
        this.url = url;
    }
}
