package com.kakao.cafe.global.interceptor;

import com.kakao.cafe.global.util.SessionUtil;
import com.kakao.cafe.user.dto.response.UserDto;
import com.kakao.cafe.user.exception.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<UserDto> user = Optional.ofNullable(SessionUtil
                .getUserSession(request.getSession()));

        if(user.isEmpty()) {
            throw new UnAuthorizedException();
        }

        return true;
    }
}
