package com.kakao.cafe.common;

@FunctionalInterface
public interface Validator<T> {
    void validate(T target);
}
