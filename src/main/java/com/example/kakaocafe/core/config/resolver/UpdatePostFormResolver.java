package com.example.kakaocafe.core.config.resolver;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.domain.post.dto.UpdatePostForm;
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

public class UpdatePostFormResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UpdatePostForm.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        final HttpSession httpSession = request.getSession();

        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final long id = Long.parseLong(pathVariables.get("postId"));
        final long writerId = ((long) httpSession.getAttribute(SessionData.USER_KEY));
        final String title = request.getParameter("title");
        final String contents = request.getParameter("contents");

        return new UpdatePostForm(id, writerId, title, contents);
    }
}
