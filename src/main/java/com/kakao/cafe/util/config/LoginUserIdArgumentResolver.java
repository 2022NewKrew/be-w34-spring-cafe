package com.kakao.cafe.util.config;

import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.util.anotation.Login;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final String LOGIN_USER = "loginUser";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isUserIdType = UserId.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && isUserIdType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession();
        return session.getAttribute(LOGIN_USER);
    }
}
