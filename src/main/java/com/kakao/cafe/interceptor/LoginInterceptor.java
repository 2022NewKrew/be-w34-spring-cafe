package com.kakao.cafe.interceptor;

import com.kakao.cafe.annotaion.LoginCheck;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

        if (loginCheck == null) {
            return true;
        }

        String sessionUserId = (String) request.getSession().getAttribute("sessionOfUser");

        if (sessionUserId != null) {
            return true;
        }

        saveDest(request);
        response.sendRedirect("/users/login");
        return false;
    }


    private void saveDest(HttpServletRequest request) {
        String destUri = request.getRequestURI();
        String destQuery = request.getQueryString();
        String dest = (destQuery == null) ? destUri : destUri + "?" + destQuery;
        request.getSession().setAttribute("dest", dest);
    }
}
