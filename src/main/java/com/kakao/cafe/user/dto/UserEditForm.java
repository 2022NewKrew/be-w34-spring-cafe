package com.kakao.cafe.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserEditForm {

    @Email
    private final String email;

    @NotBlank
    private final String displayName;

    public UserEditForm(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }
}
