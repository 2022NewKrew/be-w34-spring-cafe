package com.kakao.cafe.interfaces.common;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.interfaces.user.dto.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    private final FindUserService findUserService;

    public AuthenticationInterceptor(FindUserService findUserService) {
        this.findUserService = findUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getServletPath();
        if (!requestPath.endsWith("/update") && !requestPath.endsWith("/form")) {
            return true;
        }

        UserResponseDto user = (UserResponseDto)request.getSession().getAttribute("sessionedUser");
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String pathVariableId = String.valueOf(pathVariables.get("id"));
        logger.info("인증 요청: ");

        if (user == null || !pathVariableId.equals(user.getUserId())) {    //  로그인 안 되어 있음
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        return true;
    }
}
