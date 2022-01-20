package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final List<String> loginNeeded = List.of("/posts/**", "/write", "/update");
    public static final List<String> loginNotNeeded = List.of("/posts");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
            outputFlashMap.put("flashMessage", "로그인이 필요합니다");
            RequestContextUtils.saveOutputFlashMap("/", request, response);
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
