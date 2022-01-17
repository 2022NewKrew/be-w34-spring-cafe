package com.kakao.cafe.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kakao.cafe.exception.IllegalLoginException;
import com.kakao.cafe.exception.NoUserAuthException;

@ControllerAdvice
public class MainControllerAdvice {
	@ExceptionHandler(IllegalLoginException.class)
	public String illegalLoginException(IllegalLoginException exception) {
		exception.printStackTrace();

		return "redirect:/users/login/re";
	}

	@ExceptionHandler(NoUserAuthException.class)
	public String noUserAuthException(NoUserAuthException exception) {
		exception.printStackTrace();

		return "redirect:/";
	}
}
