package com.kakao.cafe.common.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final UrlLogger urlLogger;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void PostMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void PutMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void DeleteMapping() {
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void Service() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void Repository() {

    }

    @Around("GetMapping() || PostMapping() || DeleteMapping() || PutMapping()")
    public Object Mapping(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURI();
        urlLogger.setRequestURL(requestUrl);

        try {
            urlLogger.log("Controller 요청을 시작합니다..");
            Object result = joinPoint.proceed();
            urlLogger.log("Controller 요청을 완료하였습니다.");
            return result;
        } catch (Exception e) {
            urlLogger.log("Controller 요청중 에러가 발생했습니다. error message : " + e.getMessage());
            return null;
        }
    }

    @Around("Service()")
    public Object Service(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            urlLogger.log("Service 요청을 시작합니다.");
            Object result = joinPoint.proceed();
            urlLogger.log("Service 요청을 완료하였습니다.");
            return result;
        } catch (Exception e) {
            urlLogger.log("Service 요청중 에러가 발생했습니다. error message : " + e.getMessage());
            return null;
        }
    }

    @Around("Repository()")
    public Object Repository(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            urlLogger.log("Repository 요청을 시작합니다.");
            Object result = joinPoint.proceed();
            urlLogger.log("Repository 요청을 완료하였습니다.");
            return result;
        } catch (Exception e) {
            urlLogger.log("Repository 요청중 에러가 발생했습니다. error message : " + e.getMessage());
            return null;
        }
    }

}
