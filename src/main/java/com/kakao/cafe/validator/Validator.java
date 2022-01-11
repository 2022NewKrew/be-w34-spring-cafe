package com.kakao.cafe.validator;

public interface Validator<T> {
    void validate(T target);
}
