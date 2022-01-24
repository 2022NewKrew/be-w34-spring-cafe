package com.kakao.cafe.advice;

import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.LoginUserNotFoundException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class LoginUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserDto.UserSessionDto.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession();
        UserDto.UserSessionDto userSessionDto = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");

        if (userSessionDto == null) {
            throw new LoginUserNotFoundException();
        }

        return userSessionDto;
    }
}
