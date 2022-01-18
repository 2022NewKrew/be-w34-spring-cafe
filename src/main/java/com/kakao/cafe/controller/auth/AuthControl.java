package com.kakao.cafe.controller.auth;

import com.kakao.cafe.dto.UserDto;
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
        final String userId = getLogonId(request);
        if (userId == null) {
            return false;
        }

        try {
            userService.getDto(userId);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public static String getLogonId(@NonNull final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        return (String)session.getAttribute(TAG_ID);
    }

    public static void login(@NonNull final HttpServletRequest request, @NonNull final UserDto userDto) {
        final HttpSession sessionOld = request.getSession(false);
        if (sessionOld != null) {
            sessionOld.invalidate();
        }

        final HttpSession sessionNew = request.getSession();
        sessionNew.setAttribute(TAG_ID, userDto.getId());
        sessionNew.setAttribute(TAG_NAME, userDto.getName());
    }

    public static void logout(@NonNull final HttpServletRequest request) {
        final HttpSession sessionOld = request.getSession(false);
        if (sessionOld != null) {
            sessionOld.invalidate();
        }
    }
}
