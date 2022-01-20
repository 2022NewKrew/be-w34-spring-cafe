package com.kakao.cafe.interfaces.common;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.exception.NonExistsUserIdException;
import com.kakao.cafe.application.user.exception.UnauthorizedUserException;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.response.LoginInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    private final FindUserService findUserService;

    public AuthenticationInterceptor(FindUserService findUserService) {
        this.findUserService = findUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedUserException, NonExistsUserIdException, ServletException, IOException {
        LoginInfoDto loginInfoDto = (LoginInfoDto) request.getSession().getAttribute("sessionedUser");

        if (loginInfoDto == null) {
            response.sendRedirect("/user/login");
        }

        logger.info("로그인 유저: {}", loginInfoDto.getUserId());
        User user = findUserService.findByUserId(loginInfoDto.getUserId());
        request.setAttribute("user", user);
        return true;
    }
}
