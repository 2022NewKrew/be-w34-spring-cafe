package com.kakao.cafe.core.config.resolver;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.article.dto.ArticleUpdateDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ArticleUpdateFormResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(ArticleUpdateDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        final HttpSession session = request.getSession();

        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final Long id = Long.parseLong(pathVariables.get("id"));
        final Long userId = (Long) session.getAttribute(SessionData.USER_KEY);
        final String title = request.getParameter("title");
        final String content = request.getParameter("content");

        return new ArticleUpdateDto(id, userId, title, content);
    }
}
