package com.kakao.cafe.Util;

import javax.servlet.http.HttpSession;

public class SessionUtil {


    private SessionUtil() {
    }

    public static void checkLoginStatus(HttpSession httpSession) {
        if (httpSession.getAttribute("sessionOfUser") != null) {
            throw new IllegalStateException("이미 로그인이 된 상태입니다.");
        }
    }

    public static String getUserIdFromSession(HttpSession session) {
        return (String) session.getAttribute("sessionOfUser");
    }
}
