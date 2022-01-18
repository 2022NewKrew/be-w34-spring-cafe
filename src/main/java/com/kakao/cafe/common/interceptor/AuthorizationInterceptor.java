package com.kakao.cafe.common.interceptor;

import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.mapper.exception.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();

        Optional<UserInfoResponse> user =
                Optional.ofNullable((UserInfoResponse) session.getAttribute("user"));

        if(user.isEmpty()) {
            throw new UnAuthorizedException();
        }

        return true;
    }
}
