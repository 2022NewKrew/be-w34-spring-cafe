package com.kakao.cafe.aop;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.util.exception.NotLoggedInException;
import com.kakao.cafe.util.exception.NotMineException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class Aspects {
    private final Logger logger = LoggerFactory.getLogger(Aspects.class);

    @Before(value = "execution(* com.kakao.cafe..*.*(..))")
    public void logging(JoinPoint joinPoint) {
        logger.debug("Method Call: {}", joinPoint.getSignature());
    }

    @Before("@annotation(com.kakao.cafe.util.annotation.LoginCheck)")
    public void loginCheck(JoinPoint joinPoint) {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();

        if (session.getAttribute("USER_ID") == null) {
            throw new NotLoggedInException("로그인하지 않은 상태이므로 접근할 수 없습니다.");
        }

        String loggedInUserId = (String) session.getAttribute("USER_ID");
        Object firstArgs = joinPoint.getArgs()[0];

        if ((firstArgs instanceof String && !loggedInUserId.equals(firstArgs))
                || (firstArgs instanceof UserDto && !loggedInUserId.equals(((UserDto) firstArgs).getUserId()))) {
            throw new NotMineException("현재 회원으로 접근할 수 없습니다.");
        }
    }
}
