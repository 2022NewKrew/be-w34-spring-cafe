package com.kakao.cafe.common.aop;

import com.kakao.cafe.common.auth.Auth;
import com.kakao.cafe.common.exception.BaseException;
import com.kakao.cafe.controller.common.LoginUser;
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
    public Object beforeAdminAuth(ProceedingJoinPoint joinPoint) throws BaseException {

        try {
            Auth auth = getAuth(joinPoint);

            LoginUser user = getLoginUser();

            if (user == null || user.getRole() != UserStatus.ADMIN) {
                throw new BaseException("권한 없는 사용자가 접근했습니다.");
            }

            return joinPoint.proceed();
        } catch (BaseException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    private LoginUser getLoginUser() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        return (LoginUser) session.getAttribute("loginUser");
    }

    private Auth getAuth(ProceedingJoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        return method.getAnnotation(Auth.class);
    }
}
