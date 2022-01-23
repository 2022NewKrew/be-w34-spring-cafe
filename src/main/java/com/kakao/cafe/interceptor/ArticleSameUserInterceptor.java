package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ArticleSameUserInterceptor implements HandlerInterceptor {

    private final ArticleService articleService;

    public final List<String> sameUserEssential = Arrays.asList("/article/modify/*", "/article/delete/*");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object value = request.getSession().getAttribute("sessionedUser");

        User user = (User) value;
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String articleId = (String) pathVariables.get("articleId");
        if (articleId == null || articleId.isBlank()) return false;

        Article article = articleService.findArticleById(articleId);

        return user.isSameUser(article.getUserId());
    }
}
