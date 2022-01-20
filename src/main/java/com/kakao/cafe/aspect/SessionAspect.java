package com.kakao.cafe.aspect;

import com.kakao.cafe.dto.UserDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
@Aspect
public class SessionAspect {

    @Around("@annotation(com.kakao.cafe.annotation.SessionCheck)")
    public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = null;
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof HttpSession) {
                session = (HttpSession) o;
                break;
            }
        }
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/users/login";
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(com.kakao.cafe.annotation.RestResponseSessionCheck)")
    public Object RestResponseSessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = null;
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof HttpSession) {
                session = (HttpSession) o;
                break;
            }
        }
        if (session.getAttribute("sessionUser") == null) {
            return "로그인후 다시 시도해주세요.";
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(com.kakao.cafe.annotation.SessionUserInfoCheck)")
    public Object sessionUserInfoCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        UserDTO user = null;
        Long id = null;
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof HttpSession) {
                user = (UserDTO) ((HttpSession) o).getAttribute("sessionUser");
            } else if (o instanceof Long) {
                id = (Long) o;
            }
        }
        if (user == null || !Objects.equals(id, user.getId())) {
            return "redirect:/users/login";
        }
        return joinPoint.proceed();
    }
}
