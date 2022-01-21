package com.kakao.cafe.interceptor;

import com.kakao.cafe.common.authentification.Auth;
import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.common.exception.custom.LoginFailedException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.common.session.SessionKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) { return true; }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if(ObjectUtils.isEmpty(auth)) {
            return true;
        }

        HttpSession session = request.getSession();
        Object value = session.getAttribute(SessionKeys.USER_IDENTIFICATION);
        if(!(value instanceof UserIdentification)) {
            throw new LoginFailedException(ErrorCode.IDENTIFICATION_NOT_FOUND);
        }
        return true;
    }
}
