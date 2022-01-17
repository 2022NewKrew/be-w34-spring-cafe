package com.example.kakaocafe.core.config.resolver;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class WriteCommentFormResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(WriteCommentForm.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final HttpSession httpSession = request.getSession();
        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        final long postId = Long.parseLong(pathVariables.get("postId"));
        final long writerId = ((long) httpSession.getAttribute(SessionData.USER_KEY));
        final String contents = request.getParameter("contents");

        return new WriteCommentForm(postId, writerId, contents);
    }
}
