package com.kakao.cafe.controller.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionLoginUser {
    private static final String SESSION_LOGIN_USER = "loginUser";
    private static final String ADMIN = "admin";
    private final HttpSession session;

    public Object getLoginUser() {
        return session.getAttribute(SESSION_LOGIN_USER);
    }

    public void setLoginUser(Object o) {
        session.setAttribute(SESSION_LOGIN_USER, o);
    }

    public void setAdmin() {
        session.setAttribute(ADMIN, true);
    }

    public void invalidate() {

        session.invalidate();
        log.info("success invalidate session");
    }
}
