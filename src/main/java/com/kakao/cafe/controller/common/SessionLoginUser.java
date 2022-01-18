package com.kakao.cafe.controller.common;

import com.kakao.cafe.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class SessionLoginUser<T> {
    private static final String SESSION_LOGIN_USER = "loginUser";
    private final HttpSession session;
    private T loginUser;

    public T getLoginUser() {
        return (T) session.getAttribute(SESSION_LOGIN_USER);
    }
}
