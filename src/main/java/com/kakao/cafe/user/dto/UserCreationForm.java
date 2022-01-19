package com.kakao.cafe.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCreationForm {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @Email
    private final String email;

    @NotBlank
    private final String displayName;

    public UserCreationForm(String username, String password, String email, String displayName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }
}
