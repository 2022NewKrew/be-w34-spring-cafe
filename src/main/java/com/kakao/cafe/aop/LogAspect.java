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
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap.isEmpty()) {
            return "";
        }
        return "[ " + paramMapToString(paramMap) + "]";
    }

    private String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
        StringBuilder sb = new StringBuilder();
        paramMap.entrySet().stream().forEach(entry -> appendKeyAndValueOfEntryToStringBuilder(entry, sb));
        String stringOfParameters = sb.toString();
        stringOfParameters = stringOfParameters.substring(0, stringOfParameters.length() - 2);

        return stringOfParameters;
    }

    private void appendKeyAndValueOfEntryToStringBuilder(Entry<String, String[]> entry, StringBuilder sb) {
        sb.append(entry.getKey() + " : ");
        for (int i = 0; i < entry.getValue().length; i++) {
            sb.append(entry.getValue()[i] + " ");
        }
        sb.append(", ");
    }
}

