package com.kakao.cafe.interceptor;

import com.kakao.cafe.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final Map<String, List<String>> skipUriAndHttpMethod = Map.of("/users", List.of("POST"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("인증 체크 인터셉터 실행");

        if (checkIfCanSkipUriAndHttpMethod(request.getRequestURI(), request.getMethod())) {
            return true;
        }

        if (isNotLoginUser(request, response)) {
            response.sendRedirect("/users/loginForm");
            return false;
        }


        return true;

    }

    private boolean checkIfCanSkipUriAndHttpMethod(String uri, String httpMethod) {
        List<String> skipHttpMethod = skipUriAndHttpMethod.get(uri);
        if (skipHttpMethod != null && skipHttpMethod.contains(httpMethod)) {
            log.info("인증 체크 인터셉터 생략");
            return true;
        }
        return false;
    }

    private boolean isNotLoginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(Constants.loginUser) == null) {
            log.info("로그인 하지 않는 사용자 접근");
            return true;
        }
        return false;
    }
}
