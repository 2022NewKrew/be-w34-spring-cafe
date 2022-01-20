package com.kakao.cafe.controller;

public class UnexpectedException extends RuntimeException {

    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String msg) {
        super(msg);
    }
}
