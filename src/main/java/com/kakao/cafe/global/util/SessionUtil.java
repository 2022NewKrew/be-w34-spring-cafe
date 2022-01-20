package com.kakao.cafe.global.util;

import com.kakao.cafe.user.dto.response.UserDto;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static UserDto getUserSession(HttpSession session) {
        return (UserDto) session.getAttribute("user");
    }

    public static void saveUserSession(UserDto user, HttpSession session) {
        session.setAttribute("user", user);
    }

    public static void removeSession(HttpSession session) {
        session.invalidate();
    }
}
