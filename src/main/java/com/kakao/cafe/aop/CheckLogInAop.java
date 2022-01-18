package com.kakao.cafe.aop;

import com.kakao.cafe.domain.UserAccount;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class CheckLogInAop {
    Logger logger = LoggerFactory.getLogger(CheckLogInAop.class);

    @Around("@annotation(LogInCheck)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        Object value = session.getAttribute("sessionedUser");
        String signedUserId = "";

        if(value != null)
            signedUserId = ((UserAccount)value).getUserId();

        if("".equals(signedUserId)){
            logger.error("[CheckLogInAop] 권한 확인 중 로그인이 필요한 접근 공간에 로그인 없이 접근하였습니다.");
            return "redirect:/user/login";
        }


        return joinPoint.proceed();
    }
}
