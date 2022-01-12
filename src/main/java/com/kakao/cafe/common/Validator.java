package com.kakao.cafe.common;

public interface Validator<T> {
    void validate(T target);
}
