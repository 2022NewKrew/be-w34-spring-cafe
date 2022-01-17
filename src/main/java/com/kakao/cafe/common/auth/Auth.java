package com.kakao.cafe.common.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auth {
    Role role() default Role.NOT_LOGIN;

    enum Role {
        NOT_LOGIN,
        LOGIN_USER,
        ADMIN
    }
}
