package com.kakao.cafe.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;

@Component
@Aspect
@RequiredArgsConstructor
public class LogAspect {

    private final Logger logger;

    @Around("within(com.kakao.cafe.controller..*)")
    public Object loggingAroundController(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String stringOfParameters = getStringOfParameters(request);
        String remoteHost = getRemoteHost(request);

        long timeBeforeProceed = System.currentTimeMillis();
        try {
            return pjp.proceed(pjp.getArgs());
        } finally {
            long timeAfterProceed = System.currentTimeMillis();
            logger.info("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(), stringOfParameters,
                    remoteHost, timeAfterProceed - timeBeforeProceed);
        }
    }

    private String getStringOfParameters(HttpServletRequest request) {
        return new HttpRequestParameter(request).getStringOfParameters();
    }

    private String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }
}

