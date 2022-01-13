package com.kakao.cafe.aop.login;

import com.kakao.cafe.error.exception.nonexist.SessionAttributesNotFoundedException;
import com.kakao.cafe.error.msg.SessionErrorMsg;
import com.kakao.cafe.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginAspect {

    private final HttpSession session;

    @Before("@annotation(com.kakao.cafe.aop.login.LoginIdSessionNotNull)")
    public void checkLoginIdSessionIsNotNull() {
        Long loginUserId = SessionUtil.getLoginUserId(session);
        if(loginUserId == null) {
            throw new SessionAttributesNotFoundedException(SessionErrorMsg.SESSION_ATTRIBUTES_NOT_FOUNDED_ERROR.getDescription());
        }
    }
}
