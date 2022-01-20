package com.kakao.cafe.utils;

import com.kakao.cafe.exception.NoAuthenticationException;
import com.kakao.cafe.model.user.UserDto;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    private static final String CURRENT_USER = "currentUser";

    public static boolean isCurrentUser(HttpSession session) {
        return ObjectUtils.isEmpty(session.getAttribute(CURRENT_USER));
    }

    public static UserDto getCurrentUser(HttpSession session) {
        UserDto currentUser = (UserDto) session.getAttribute(CURRENT_USER);
        if (currentUser == null) {
            throw new NoAuthenticationException();
        }
        return currentUser;
    }

    public static long getCurrentUserId(HttpSession session) {
        return getCurrentUser(session).getId();
    }

}
