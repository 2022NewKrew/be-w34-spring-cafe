package com.kakao.cafe.aop;

import com.kakao.cafe.domain.UserAccount;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class CheckUserAuthAop {
    Logger logger = LoggerFactory.getLogger(CheckUserAuthAop.class);

    @Around("@annotation(AuthCheck)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        String userId = "";
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();

        for(int i = 0 ; i < paramNames.length ; i++){
            String paramName = paramNames[i];

            if(paramName.equals("userId"))
                userId = (String) joinPoint.getArgs()[i];
        }

        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        Object value = session.getAttribute("sessionedUser");
        String signedUserId = "";

        if(value != null)
            signedUserId = ((UserAccount)value).getUserId();

        if(!userId.equals(signedUserId)){
            logger.error("[checkUserAuth] 권한 확인 중 잘못된 권한으로 접근하였습니다");
            return "/";
        }


        return joinPoint.proceed();
    }
}
