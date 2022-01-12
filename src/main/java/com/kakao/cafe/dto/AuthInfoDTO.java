package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AuthInfoDTO {

    @Getter
    @AllArgsConstructor
    class Login {

        @NotBlank
        String uid;
        @NotBlank
        String password;
    }
}
