package com.kakao.cafe.common.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final String message;

    public EntityNotFoundException(String message) {
        this.message = message;
    }

    public static <T, U> void throwNotExistsByField(Class<T> tClass, String property, U value)
            throws EntityNotFoundException
    {
        throw new EntityNotFoundException(property + "에 해당하는 " + tClass.getName() + "이(가) 존재하지 않습니다. value: " + value);
    }
}
