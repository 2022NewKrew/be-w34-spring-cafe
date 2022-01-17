package com.kakao.cafe.exception;

public class NoUserAuthException extends RuntimeException {
	public NoUserAuthException(String message) {
		super(message);
	}
}
