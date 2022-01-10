package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserDTO {

    @Getter
    @AllArgsConstructor
    class Create {

        String userId;
        String password;
        String name;
        String email;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class Result {

        Integer id;
        String userId;
        String name;
        String email;

        public static Result from(User user) {
            return new Result(user.getId(), user.getUserId(), user.getName(), user.getEmail());
        }
    }
}
