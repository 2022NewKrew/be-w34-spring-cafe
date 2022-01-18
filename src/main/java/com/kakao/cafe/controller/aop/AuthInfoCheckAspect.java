package com.kakao.cafe.controller.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AuthInfoCheckAspect {

    @Before("@annotation(AuthInfoCheck)")
    public void authInfoCheck(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        if (session.getAttribute("authInfo") == null) {
            throw new IllegalArgumentException("로그인 하시기 바랍니다.");
        }
    }














}
