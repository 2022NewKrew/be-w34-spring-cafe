package com.kakao.cafe.user.interceptor;


import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.user.dto.LoggedInUser;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            // Check Method-wise annotation
            NeedLogin needLogin = ((HandlerMethod) handler).getMethodAnnotation(NeedLogin.class);

            // Check Class-wise annotation
            if (needLogin == null) {
                needLogin = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(NeedLogin.class);
            }

            // If annotated
            if (needLogin != null) {
                LoggedInUser loggedInUser = (LoggedInUser) request.getSession().getAttribute("loggedInUser");

                if (loggedInUser == null) {
                    throw new UnauthorizedAccessException();
                }
                request.setAttribute("loggedInUser", loggedInUser);
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        LoggedInUser loggedInUser = (LoggedInUser) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null && modelAndView != null) {
            modelAndView.addObject("loggedInUser", loggedInUser);
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
