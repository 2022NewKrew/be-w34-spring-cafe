package com.kakao.cafe.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final Logger log;

    public LogInterceptor() {
        this.log = LoggerFactory.getLogger(LogInterceptor.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Incoming Request: method={}, uri={}",
                request.getMethod(),
                request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
