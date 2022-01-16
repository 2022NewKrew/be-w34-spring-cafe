package com.example.kakaocafe.core.config.resolver;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.domain.post.dto.WritePostForm;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WritePostFormResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(WritePostForm.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        final HttpSession httpSession = request.getSession();

        final long writerId = ((long) httpSession.getAttribute(SessionData.USER_KEY));
        final String title = request.getParameter("title");
        final String contents = request.getParameter("contents");

        return new WritePostForm(writerId, title, contents);
    }
}
