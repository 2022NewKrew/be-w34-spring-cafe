package com.kakao.cafe.interceptor;

import com.kakao.cafe.dto.AuthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public List<String> pathPatterns = Arrays.asList("/accounts/mypage/**", "/accounts/delete", "/articles/new");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        AuthDto authDto = (AuthDto) session.getAttribute("auth");
        if (authDto == null) {
            log.debug("[Login Interceptor] An unauthorized user attempted to access on \"" + request.getRequestURI() + "\"");
            FlashMap flashMap = new FlashMap();
            flashMap.put("message", "로그인이 필요한 페이지입니다.");
            FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
            flashMapManager.saveOutputFlashMap(flashMap, request, response);
            response.sendRedirect("/accounts/login");
            return false;
        }
        return true;
    }
}
