package com.kakao.cafe.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class UserLoginCheckAspect {

	@Around("@annotation(com.kakao.cafe.aop.UserLoginCheck)")
	public Object userLoginCheck(ProceedingJoinPoint point) throws Throwable {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
		if (session.getAttribute("sessionUser") == null) {
			return "redirect:/users/login";
		}
		return point.proceed();
	}
}
