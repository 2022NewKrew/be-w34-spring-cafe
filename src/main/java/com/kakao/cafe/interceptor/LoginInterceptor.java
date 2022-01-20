package com.kakao.cafe.interceptor;

import com.kakao.cafe.users.UserController;
import com.kakao.cafe.users.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        Optional<UserDto> sessionedUser = Optional.ofNullable((UserDto)session.getAttribute("sessionedUser"));
        if(!sessionedUser.isPresent()) {
            LOGGER.info("로그인이 필요합니다!");
            response.sendRedirect("/user/login");
            return false;
        }
        request.setAttribute("userSeq", sessionedUser.get().getUserSeq());
        return true;
    }

}
