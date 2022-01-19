package com.kakao.cafe.common.util;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

    public static final String SESSION_USER = "session_user";

    public void add(Object value, HttpSession session) {
        session.setAttribute(SESSION_USER, value);
    }

    public void remove(HttpSession session) {
        session.invalidate();
    }
}
