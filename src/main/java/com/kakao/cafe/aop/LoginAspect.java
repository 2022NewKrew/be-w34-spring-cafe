package com.kakao.cafe.aop;

import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAspect.class);

    @Before("@annotation(com.kakao.cafe.util.annotation.LoginCheck)")
    public void loginCheck() throws UnauthorizedActionException {
        LOGGER.info("loginCheck() : start");
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        if (session.getAttribute("sessionId") == null) {
            LOGGER.error("loginCheck() : seesionId == null");
            throw new UnauthorizedActionException("로그인 한 뒤 이용해주세요!");
        }
        LOGGER.info("loginCheck() : done");
    }


}
