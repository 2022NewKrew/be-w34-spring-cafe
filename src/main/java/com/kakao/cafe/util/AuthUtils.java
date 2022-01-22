package com.kakao.cafe.util;

import com.kakao.cafe.domain.user.User;

import javax.servlet.http.HttpSession;

public class AuthUtils {

    public static Long checkLogin(HttpSession session) {
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION);
        if (user == null) {
            throw new IllegalStateException(ErrorMessage.NO_AUTH.getMsg());
        }

        return user.getUserId();
    }
}
