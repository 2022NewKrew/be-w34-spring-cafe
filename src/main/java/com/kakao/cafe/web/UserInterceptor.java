package com.kakao.cafe.web;

import com.kakao.cafe.model.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("currentUser");
        String id = getIdPathVariableFromRequest(request);
        if (!id.equals(String.valueOf(user.getId()))) {
            response.sendError(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }

    private String getIdPathVariableFromRequest(HttpServletRequest request) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return pathVariables.get("id");
    }
}
