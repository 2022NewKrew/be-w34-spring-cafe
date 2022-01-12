package com.example.kakaocafe.core.config.interceptor;

import com.example.kakaocafe.core.meta.SessionAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        final HttpSession httpSession = request.getSession();

        assert modelAndView != null;
        final Object userSessionDTO = modelAndView.getModel().get(SessionAttributes.USER_KEY.getValue());

        if (userSessionDTO != null) {
            httpSession.setAttribute(SessionAttributes.USER_KEY.getValue(), userSessionDTO);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        final HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute(SessionAttributes.USER_KEY.getValue()) != null) {
            httpSession.removeAttribute(SessionAttributes.USER_KEY.getValue());
        }

        return true;
    }
}
