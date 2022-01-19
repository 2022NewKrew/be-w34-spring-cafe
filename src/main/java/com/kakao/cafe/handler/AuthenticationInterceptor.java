package com.kakao.cafe.handler;

import com.kakao.cafe.annotation.UserAuthorized;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.exception.UnauthorizedAccessException;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth == null) {
            response.sendRedirect("/user/login");
            return false;
        }

        if (handler instanceof HandlerMethod) {
            handleUserAuthorization(request, handler, auth);
        }

        return true;
    }

    private void handleUserAuthorization(HttpServletRequest request, Object handler, Auth auth) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        UserAuthorized userAuthorized = handlerMethod.getMethodAnnotation(UserAuthorized.class);
        if (userAuthorized != null) {
            Map<String, String> pathVariableMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            validateArticle(pathVariableMap, auth);
            validateUser(pathVariableMap, auth);
        }
    }

    private void validateArticle(Map<String, String> pathVariableMap, Auth auth) {
        if (pathVariableMap.containsKey("articleId")) {
            long articleId = Long.parseLong(pathVariableMap.get("articleId"));
            ArticleDto article = articleService.findArticleById(articleId);
            throwIfNotAuthorized(auth, article.getAuthorId());
        }
    }

    private void validateUser(Map<String, String> pathVariableMap, Auth auth) {
        if (pathVariableMap.containsKey("userId")) {
            long userId = Long.parseLong(pathVariableMap.get("userId"));
            throwIfNotAuthorized(auth, userId);
        }
    }

    private void throwIfNotAuthorized(Auth auth, Long id) {
        if (auth.isNotValidId(id)) {
            throw new UnauthorizedAccessException("인가되지 않은 접근입니다.");
        }
    }
}
