package com.kakao.cafe.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.kakao.cafe.*.*(..))")
    private void pointcutMethod () {}

    @Before("pointcutMethod()")
    void writeLog (JoinPoint joinPoint) {
        logger.info("===============AOP BEFORE::" + joinPoint.getSignature().getName());
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void pointcutGet () {}
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void pointcutPost () {}

    @Before("pointcutGet() || pointcutPost()")
    void writeMappingLog (JoinPoint joinPoint) {
        logger.info("===============AOP MAPPING - BEFORE::" + joinPoint.getSignature().getName());
    }
}
