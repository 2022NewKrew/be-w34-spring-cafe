package com.kakao.cafe.common.log;

import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
//    TODO: Controller, Service, Repository 로그 구현
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void GetMapping(){ }
//
//    @Before("GetMapping()")
//    public void before(JoinPoint joinPoint) {
//        log.info("=====================AspectJ TEST  : Before Logging Start=====================");
//        log.info("=====================AspectJ TEST  : Before Logging End=====================");
//    }
//
//    @AfterReturning(pointcut = "GetMapping()", returning = "result")
//    public void AfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
//        log.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
//    }
//
//    @Around("GetMapping()")
//    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("=====================AspectJ TEST  : Around Logging Start=====================");
//        try {
//            Object result = joinPoint.proceed();
//            log.info("=====================AspectJ TEST  : Around Logging END=====================");
//            return result;
//        }catch (Exception e) {
//            log.error("=====================AspectJ Around Exception=====================");
//            log.error(e.toString());
//            return null;
//        }
//    }
}
