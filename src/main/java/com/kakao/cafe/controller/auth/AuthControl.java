package com.kakao.cafe.controller.auth;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

public class AuthControl {

    private AuthControl() {}

    public static boolean isLogon(@NonNull final HttpSession session, @NonNull final UserService userService) {
        String userStr = (String)session.getAttribute("curUserId");
        if (userStr == null) {
            return false;
        }

        try {
            userService.getUser(userStr);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public static void login(@NonNull final HttpSession session, @NonNull final User user) {
        session.setAttribute("curUserId", user.getId());
        session.setAttribute("curUserName", user.getName());
    }

    public static void logout(@NonNull final HttpSession session) {
        session.removeAttribute("curUserId");
        session.removeAttribute("curUserName");
    }
}
