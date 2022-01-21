package com.kakao.cafe.aspect;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.util.Util;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
@Aspect
public class SessionAspect {
    private static final String ACCESS_DENIED_MESSAGE = "다른유저의 정보에는 접근할 수 없습니다.";

    @Around("@annotation(com.kakao.cafe.annotation.SessionCheck)")
    public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = Util.getSession();

        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/users/login";
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(com.kakao.cafe.annotation.RestResponseSessionCheck)")
    public Object RestResponseSessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = Util.getSession();

        if (session.getAttribute("sessionUser") == null) {
            return "로그인후 다시 시도해주세요.";
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(com.kakao.cafe.annotation.SessionUserInfoCheck)")
    public Object sessionUserInfoCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        UserDTO user = (UserDTO) Util.getSession().getAttribute("sessionUser");
        Long id = null;
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof Long) {
                id = (Long) o;
                break;
            }
        }
        if (user == null) {
            return "redirect:/users/login";
        }
        if (!Objects.equals(id, user.getId())) {
            throw new NoModifyPermissionException(ACCESS_DENIED_MESSAGE);
        }
        return joinPoint.proceed();
    }

}
