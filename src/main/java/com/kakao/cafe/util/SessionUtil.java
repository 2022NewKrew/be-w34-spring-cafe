package com.kakao.cafe.util;

import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpSession;

public final class SessionUtil {

    public static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    public static final String AFTER_LOGIN_REDIRECTED_URL = "AFTER_LOGIN_REDIRECTED_URL";

    private SessionUtil() {
    }

    // ------------------- LOGIN_USER_ID ---------------------------------------

    public static void setLoginUserId(HttpSession session, Long id) {
        session.setAttribute(LOGIN_USER_ID, id);
    }

    public static Long getLoginUserId(HttpSession session) {
        return (Long) session.getAttribute(LOGIN_USER_ID);
    }

    // ------------------- AFTER_LOGIN_REDIRECTED_URL  ---------------------------------------

    public static void setAfterLoginRedirectedUrl(HttpSession session, String url) {
        session.setAttribute(AFTER_LOGIN_REDIRECTED_URL, url);
    }

    public static String getAfterLoginRedirectedUrl(HttpSession session) {
        return (String) session.getAttribute(AFTER_LOGIN_REDIRECTED_URL);
    }

    public static void removeAfterLoginRedirectedUrl(HttpSession session) {
        session.removeAttribute(AFTER_LOGIN_REDIRECTED_URL);
    }

    // -------------------- AUTHENTICATION ------------------------------------------------

    public static void removeAuthenticationExceptionAttributes(HttpSession session) {
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }


    // -------------------- FOR ALL ------------------------------------------------

    public static void clearSession(HttpSession session) {
        session.invalidate();
    }

}
