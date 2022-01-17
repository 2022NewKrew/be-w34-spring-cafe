package com.kakao.cafe.exception;

public class IllegalLoginException extends RuntimeException {
	public IllegalLoginException(String message) {
		super(message);
	}
}
