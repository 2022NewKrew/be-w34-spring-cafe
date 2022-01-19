package com.kakao.cafe.util;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.annotation.Auth;
import com.kakao.cafe.util.annotation.AuthMyArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthMyArticleInterceptor implements HandlerInterceptor {

    private final ArticleService articleService;

    Logger logger = LoggerFactory.getLogger(AuthMyArticleInterceptor.class);

    public AuthMyArticleInterceptor(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        AuthMyArticle authMyArticle = handlerMethod.getMethodAnnotation(AuthMyArticle.class);

        if (authMyArticle == null) {
            return true;
        }

        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("sessionedUser");
        if (user == null) {
            response.sendRedirect("/users/login");
            return false;
        }

        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String index = (String) pathVariables.get("index");
        ArticleDto article = articleService.filterArticleByIndex(Integer.parseInt(index));
        logger.info(article.getWriter().getUserId());
        logger.info(user.getUserId());
        if (!user.getUserId().equals(article.getWriter().getUserId())) {
            throw new NotAllowedUserException();
        }

        return true;
    }
}
