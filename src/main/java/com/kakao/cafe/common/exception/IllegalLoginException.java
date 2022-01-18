package com.kakao.cafe.common.exception;

public class IllegalLoginException extends RuntimeException {
	public IllegalLoginException(String message) {
		super(message);
	}
}
