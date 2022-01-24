package com.kakao.cafe.aop;

import com.kakao.cafe.dto.UserProfileDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class UserCheckAspect {

    @Around("@annotation(UserAuthCheck)")
    public Object userAuthCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        Object value = session.getAttribute("sessionedUser");

        if (value == null) // 로그인 x
            return "redirect:/users/login";

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        String userIdParam = null;
        for (int i = 0; i < parameterNames.length; i++) {
            if ("userId".equals(parameterNames[i])) {
                userIdParam = (String) joinPoint.getArgs()[i];
                break;
            }
        }

        UserProfileDto userProfileDto = (UserProfileDto) value;
        if (!userProfileDto.getUserId().equals(userIdParam)) { // 접근 권한 없음 (타 유저 정보)
            return "redirect:/";
        }

        return joinPoint.proceed();
    }
}
