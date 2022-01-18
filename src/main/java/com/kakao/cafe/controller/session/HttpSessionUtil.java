package com.kakao.cafe.controller.session;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {

    public static AuthInfo getAuthInfo(HttpSession session) {
        return (AuthInfo) session.getAttribute("authInfo");
    }
}
