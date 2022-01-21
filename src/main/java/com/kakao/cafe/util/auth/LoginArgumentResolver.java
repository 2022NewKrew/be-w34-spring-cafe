package com.kakao.cafe.util.auth;

import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.dto.user.UserResponse;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(SessionUser.class) &&
                parameter.hasParameterAnnotation(LoginCheck.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionedUser");

        if(userResponse == null){
            return null;
//            throw new IllegalAccessException("로그인 정보가 없습니다.");
        }

        return SessionUser.builder()
                .id(userResponse.getId())
                .userId(userResponse.getUserId())
                .build();
    }
}
