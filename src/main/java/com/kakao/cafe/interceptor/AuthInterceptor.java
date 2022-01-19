package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final List<HttpRequest> urlPattern = new ArrayList<>();

    public AuthInterceptor() {
        urlPattern.add(new HttpRequest("GET", "/questions/{}"));
        urlPattern.add(new HttpRequest("POST", "/questions"));
        urlPattern.add(new HttpRequest("GET", "/qna/form"));
        urlPattern.add(new HttpRequest("POST", "/questions/{}/answers"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("sessionedUser");

        if (loginMember == null & match(request.getMethod(), request.getRequestURI())) {
            response.sendRedirect(request.getContextPath() + "/member/login");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean match(String method, String requestURI) {
        return urlPattern.stream()
                .anyMatch(url -> url.equals(method, requestURI));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
