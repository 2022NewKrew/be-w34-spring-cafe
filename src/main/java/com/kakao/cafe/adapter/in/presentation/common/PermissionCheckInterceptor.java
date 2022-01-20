package com.kakao.cafe.adapter.in.presentation.common;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class PermissionCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession(false);
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        if (!sessionedUser.getUserId().equals(userId)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        return true;
    }
}
