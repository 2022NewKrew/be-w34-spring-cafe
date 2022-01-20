package com.kakao.cafe.util;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.exception.user.UserUnauthorizedException;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import com.kakao.cafe.util.annotation.Auth;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        if (auth == null) {
            return true;
        }

        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new UserUnauthorizedException();
        }

        return true;
    }
}
