package com.kakao.cafe.handler;

import com.kakao.cafe.annotation.UserAuthorized;
import com.kakao.cafe.annotation.UserAuthorized.AuthCode;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            handleUserAuthorization(request, (HandlerMethod) handler);
        }
        return true;
    }

    private void handleUserAuthorization(HttpServletRequest request, HandlerMethod handler) {
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        UserAuthorized userAuthorized = handler.getMethodAnnotation(UserAuthorized.class);
        if (userAuthorized != null) {
            Map<String, String> pathVariableMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            AuthCode[] target = userAuthorized.target();
            validateByTarget(pathVariableMap, auth, target);
        }
    }

    private void validateByTarget(Map<String, String> pathVariableMap, Auth auth, AuthCode[] target) {
        for (AuthCode authCode : target) {
            authCode.validate(pathVariableMap, auth, articleService);
        }
    }
}
