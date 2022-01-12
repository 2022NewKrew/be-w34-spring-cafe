package com.kakao.cafe.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.kakao.cafe..*.*(..))")
    public void logging(JoinPoint joinPoint) {
        logger.debug("Method Call: {}", joinPoint.getSignature());
    }
}
