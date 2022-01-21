package com.kakao.cafe.core.config.resolver;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.user.dto.UserUpdateDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserUpdateFormResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserUpdateDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        final HttpSession session = request.getSession();

        final Long id = (Long) session.getAttribute(SessionData.USER_KEY);
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String nickname = request.getParameter("nickname");

        return new UserUpdateDto(id, email, password, nickname);
    }
}
