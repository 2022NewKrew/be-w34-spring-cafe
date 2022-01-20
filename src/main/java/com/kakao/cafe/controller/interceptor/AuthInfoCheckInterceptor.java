package com.kakao.cafe.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kakao.cafe.config.Constant;
import com.kakao.cafe.controller.session.AuthInfo;

@Component
public class AuthInfoCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        AuthInfoCheck authInfoCheck = ((HandlerMethod) handler).getMethodAnnotation(AuthInfoCheck.class);
        if (authInfoCheck == null) {
            return true;
        }
        HttpSession session = request.getSession();
        AuthInfo authInfo = (AuthInfo) session.getAttribute(Constant.authAttributeName);

        if (authInfo == null) {
            throw new IllegalArgumentException("로그인 하시기 바랍니다.");
        }
        return true;
    }
}
