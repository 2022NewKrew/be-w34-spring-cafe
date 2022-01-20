package com.kakao.cafe.resolver;

import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final static String SIGN_IN_USER = "signInUser";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("======= @LoginUser annotation exist checking ========");
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User loginUser = (User) webRequest.getAttribute(SIGN_IN_USER, WebRequest.SCOPE_SESSION);
        log.info("======= @LoginUser annotation : "+loginUser);
        return loginUser != null ? loginUser.getUserId() : null;
    }
}
