package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class UserDTO {

    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String email;
}
