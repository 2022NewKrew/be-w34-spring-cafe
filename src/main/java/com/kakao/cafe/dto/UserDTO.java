package com.kakao.cafe.dto;

import com.kakao.cafe.persistence.model.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserDTO {

    @Getter
    @AllArgsConstructor
    class Create {

        @NotBlank
        String uid;
        @NotBlank
        String password;
        @NotBlank
        String name;
        @NotBlank @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+\\w{2,3}")
        String email;
    }

    @Getter
    @AllArgsConstructor
    class Update {

        @NotBlank
        String password;
        @NotBlank
        String name;
        @NotBlank @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+\\w{2,3}")
        String email;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class Result {

        Long id;
        String uid;
        String name;
        String email;

        public static Result from(User user) {
            return new Result(user.getId(), user.getUid(), user.getName(), user.getEmail());
        }
    }
}
