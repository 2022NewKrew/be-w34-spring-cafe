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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    private final FindUserService findUserService;

    public AuthenticationInterceptor(FindUserService findUserService) {
        this.findUserService = findUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedUserException, NonExistsUserIdException {
        LoginInfoDto loginInfoDto = (LoginInfoDto) request.getSession().getAttribute("sessionedUser");

        if (loginInfoDto == null) {    //  로그인 안 되어 있음
            throw new UnauthorizedUserException();
//            request.getRequestDispatcher("/").forward(request, response);
//            return false;
        }

        logger.info("로그인 유저: {}", loginInfoDto.getUserId());
        User user = findUserService.findByUserId(loginInfoDto.getUserId());
        request.setAttribute("user", user);
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        attributes.setAttribute("user", user, RequestAttributes.SCOPE_REQUEST);
        return true;
    }
}
