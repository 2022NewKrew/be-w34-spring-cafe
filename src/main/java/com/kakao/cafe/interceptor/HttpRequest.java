package com.kakao.cafe.interceptor;

public class HttpRequest {
    private String method;
    private String url;

    public HttpRequest() {
    }

    public HttpRequest(String method, String url) {
        this.method = method;
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public boolean equals(String method, String requestURI) {
        String[] parsedURI = url.split("/");
        String[] parsedRequestURI = requestURI.split("/");

        if (!this.method.equals(method) || parsedRequestURI.length != parsedURI.length)
            return false;

        for (int i = 0; i < parsedURI.length; i++) {
            if (parsedURI[i].equals("{}"))
                continue;

            if (!parsedRequestURI[i].equals(parsedURI[i]))
                return false;
        }
        return true;
    }
}
