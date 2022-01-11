package com.kakao.cafe.module.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDtos {

    @Getter
    @AllArgsConstructor
    public static class UserDto {

        private final Long id;
        private final String userId;
        private final String password;
        private final String name;
        private final String email;
    }

    @Getter
    @AllArgsConstructor
    public static class UserSignUpDto {

        private final String userId;
        private final String password;
        private final String name;
        private final String email;
    }

    @Getter
    @AllArgsConstructor
    public static class UserUpdateDto {

        private final Long id;
        private final String password;
        private final String newPassword;
        private final String name;
        private final String email;
    }
}
