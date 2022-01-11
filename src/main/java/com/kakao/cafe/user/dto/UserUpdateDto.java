package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserUpdateDto {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;

    public String getUserId() {
        return userId;
    }
}
