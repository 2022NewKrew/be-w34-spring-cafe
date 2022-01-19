package com.kakao.cafe.adapter.in.presentation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.kakao.cafe.adapter.in.presentation.*.*.* (..))")
    public Object controllerMethodLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(
            "start - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName()
        );
        Object result = joinPoint.proceed();
        log.info(
            "finished - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName()
        );
        return result;
    }
}
