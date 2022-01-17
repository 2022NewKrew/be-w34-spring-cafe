package com.kakao.cafe.common.aop;

import com.kakao.cafe.common.auth.Auth;
import com.kakao.cafe.user.UserStatus;
import com.kakao.cafe.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@RequiredArgsConstructor
@Component
@Slf4j
public class AuthAspect {

    @Pointcut("@annotation(com.kakao.cafe.common.auth.Auth)")
    public void authAdmin() {
    }

    @Around("authAdmin()")
    public Object beforeAdminAuth(ProceedingJoinPoint joinPoint) {

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Auth auth = method.getAnnotation(Auth.class);
            Object result = joinPoint.proceed();

            if (auth.role() == Auth.Role.ADMIN) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                HttpSession session = request.getSession();
                UserDto user = (UserDto) session.getAttribute("loginUser");

                if (user == null || user.getRole() != UserStatus.ADMIN) {
                    throw new Exception("권한 없는 사용자가 접근했습니다.");
                }
            }

            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
