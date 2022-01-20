package com.kakao.cafe.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {
    public static List<String> loginCheckList = List.of("/article/**");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginId = (String) request.getSession().getAttribute("LOGIN_USER_ID");
        if(loginId != null){
            return true;
        } else{
            String destUri = request.getRequestURI();
            String destQuery = request.getQueryString();
            String dest = (destQuery == null) ? destUri : destUri+"?"+destQuery;
            log.info("dest = {}", dest);
//            request.getSession().setAttribute("dest", dest);

            response.sendRedirect("/user/login");
            return true;
        }
    }
}
