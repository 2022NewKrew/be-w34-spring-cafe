package com.kakao.cafe.aop;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequiredArgsConstructor
@Aspect
@Component
public class UserValidCheckAspect {

	private final ArticleService articleService;

	@Before("@annotation(com.kakao.cafe.aop.UserValidCheck)")
	public void userValidCheck(JoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		String[] argNames = ((MethodSignature) point.getSignature()).getParameterNames();
		int id = (int) args[0];
		if (Objects.equals(argNames[0], "index")) {
			id = articleService.findByIndex(id).getWriterObj().getId();
		}
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
		UserDto user = (UserDto) session.getAttribute("sessionUser");
		if (id != user.getId()) {
			throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다.") {
			};
		}
	}
}
