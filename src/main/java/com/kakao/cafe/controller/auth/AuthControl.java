package com.kakao.cafe.controller.auth;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

public class AuthControl {
    public static final String TAG_ID = "curUserId";
    public static final String TAG_NAME = "curUserName";

    private AuthControl() {}

    public static boolean isLogon(@NonNull final HttpServletRequest request, @NonNull final UserService userService) {
        final HttpSession session = request.getSession();
        final String userStr = (String)session.getAttribute(TAG_ID);
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

    public static void login(@NonNull final HttpServletRequest request, @NonNull final User user) {
        final HttpSession sessionOld = request.getSession(false);
        if (sessionOld != null) {
            sessionOld.invalidate();
        }

        final HttpSession sessionNew = request.getSession();
        sessionNew.setAttribute(TAG_ID, user.getId());
        sessionNew.setAttribute(TAG_NAME, user.getName());
    }

    public static void logout(@NonNull final HttpServletRequest request) {
        final HttpSession sessionOld = request.getSession(false);
        if (sessionOld != null) {
            sessionOld.invalidate();
        }
    }
}
