package com.kakao.cafe.aspect;

import com.kakao.cafe.exception.LoginRequiredException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Aspect
@Component
public class AuthorizationAspect {

    @Before("@annotation(com.kakao.cafe.annotation.LoginRequired)")
    public void checkIfLoggedIn() {
        Object loggedInUser = RequestContextHolder.currentRequestAttributes()
                .getAttribute("loggedInUser", RequestAttributes.SCOPE_SESSION);
        if (loggedInUser == null) {
            throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
        }
    }
}
