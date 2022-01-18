package com.kakao.cafe.aop;

import com.kakao.cafe.util.Url;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class Aspects {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingPoint() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPoint() {
    }

    @Around("getMappingPoint() || postMappingPoint()")
    public Object methodTimer(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("Method Called ... {} ... {}ms", joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
        return result;
    }

    @Around("@annotation(com.kakao.cafe.util.SessionIdRequired)")
    public Object checkSessionId(ProceedingJoinPoint pjp) throws Throwable {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();

        if (session.getAttribute("sessionedUserId") == null) {
            findRedirectAttribuesParameter(pjp)
                    .ifPresent(o -> o.addFlashAttribute("errorMsg", "로그인이 필요한 서비스입니다."));

            return "redirect:" + Url.USER_LOGIN;
        }

        return pjp.proceed();
    }

    private Optional<RedirectAttributes> findRedirectAttribuesParameter(ProceedingJoinPoint pjp) {
        return Arrays.stream(pjp.getArgs())
                .filter(o -> o instanceof RedirectAttributes)
                .map(o -> (RedirectAttributes) o)
                .findAny();
    }
}
