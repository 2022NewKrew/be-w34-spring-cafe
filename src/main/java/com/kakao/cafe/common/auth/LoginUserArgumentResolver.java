package com.kakao.cafe.common.auth;

import com.kakao.cafe.common.exception.UnauthorizedException;
import com.kakao.cafe.common.util.SessionUtil;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Object attribute = webRequest.getAttribute(SessionUtil.SESSION_USER,
            RequestAttributes.SCOPE_SESSION);
        if (Objects.isNull(attribute)) {
            throw new UnauthorizedException();
        }
        return attribute;
    }
}