package com.kakao.cafe.global.interceptor;

import com.kakao.cafe.user.dto.response.UserDto;
import com.kakao.cafe.user.exception.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class AuthLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Optional<UserDto> user = Optional.ofNullable((UserDto) session.getAttribute("user"));

        if(user.isEmpty()) {
            throw new UnAuthorizedException();
        }

        return true;
    }
}
