package com.kakao.cafe.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kakao.cafe.common.Constant;
import com.kakao.cafe.common.SessionUser;
import com.kakao.cafe.common.annotation.LoginCheck;
import com.kakao.cafe.common.exception.UserAuthException;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        LoginCheck loginCheck = ((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class);

        if (ObjectUtils.isEmpty(loginCheck)) {
            return true;
        }

        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute(Constant.SESSION_USER);

        if (ObjectUtils.isEmpty(user)) {
            throw new UserAuthException("로그인이 필요합니다.");
        }

        return true;
    }
}
