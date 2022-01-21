package com.kakao.cafe.core.config.resolver;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.comment.dto.CommentCreateDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CommentCreateFormResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(CommentCreateDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        final HttpSession session = request.getSession();

        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final Long userId = (Long) session.getAttribute(SessionData.USER_KEY);
        final Long articleId = Long.parseLong(pathVariables.get("articleId"));
        final String content = request.getParameter("content");

        return new CommentCreateDto(userId, articleId, content);
    }
}
