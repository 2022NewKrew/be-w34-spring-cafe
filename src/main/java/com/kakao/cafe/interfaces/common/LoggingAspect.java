package com.kakao.cafe.interfaces.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.kakao.cafe.application..*.*(..))")
    public void logging(JoinPoint joinPoint) {
        logger.info("호출 패키지: [{}], 호출 메서드 : [{}], 전달 파라미터 : {}", joinPoint.getSourceLocation().getWithinType() ,joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
}
