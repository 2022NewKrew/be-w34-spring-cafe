package com.kakao.cafe.utils;

import com.kakao.cafe.exception.NoAuthenticationException;
import com.kakao.cafe.model.user.UserDto;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static UserDto getCurrentUser(HttpSession session) {
        UserDto currentUser = (UserDto) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new NoAuthenticationException();
        }
        return currentUser;
    }

    public static long getCurrentUserId(HttpSession session) {
        return getCurrentUser(session).getId();
    }

}
