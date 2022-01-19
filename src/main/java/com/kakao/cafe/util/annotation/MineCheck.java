package com.kakao.cafe.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MineCheck {
    Type type() default Type.ARTICLE;

    enum Type {
        ARTICLE, COMMENT
    }
}
