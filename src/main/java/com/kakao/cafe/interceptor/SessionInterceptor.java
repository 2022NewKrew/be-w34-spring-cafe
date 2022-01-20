package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.exception.InvalidUserException;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SessionInterceptor implements HandlerInterceptor {

    public final List<String> loginEssential =
            Arrays.asList("/user/profile/**", "/user/logout",  "/article/*", "/article/**");

    private final String[] sameUserEssential = {"/article/modify/*", "/article/delete/*"};

    private final ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object value = request.getSession().getAttribute("sessionedUser");

        // 세션 있는지 검사
        if (Objects.isNull(value)) {
            response.sendRedirect("/user/login");
            return false;
        }

        // 본인 인증이 필요한 uri인지 검사
        if (!PatternMatchUtils.simpleMatch(sameUserEssential, request.getRequestURI())) {
            return true;
        }

        // 본인 검사 - article에서만 현재 필요, reply 구현 예정
        User user = (User) value;
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String articleId = (String) pathVariables.get("id");
        if (articleId == null || articleId.isBlank()) return false;

        Article article = articleService.findArticleById(articleId);

        return user.isSameUser(article.getUserId());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
