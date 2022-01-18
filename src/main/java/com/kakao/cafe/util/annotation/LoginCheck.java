package com.kakao.cafe.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginCheck {
    enum UserType {
        USER, ADMIN
    }

    UserType type() default UserType.USER;
}
