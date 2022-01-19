package com.kakao.cafe.aop;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.exception.UnauthenticatedException;
import com.kakao.cafe.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@Slf4j
public class LoginCheckAop {

    @Pointcut("@annotation(com.kakao.cafe.aop.annotation.LoginCheckAnnotation)")
    private void enableLoginCheck() {
    }

    @Pointcut("@annotation(com.kakao.cafe.aop.annotation.AuthCheckAnnotation)")
    private void enableAuthCheck() {
    }

    @Before("enableLoginCheck()")
    public void loginCheck() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            throw new UnauthenticatedException(ErrorMessages.NOT_AUTHENTICATED_USER);
        }
    }

    @Before("enableAuthCheck()")
    public void authCheck() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            throw new UnauthenticatedException(ErrorMessages.NOT_AUTHENTICATED_USER);
        }

        String memberId = request.getParameter("memberId");
        if (!loginMember.getMemberId().toString().equals(memberId))
            throw new UnauthorizedException(ErrorMessages.NOT_AUTHORIZED_USER);
    }
}
