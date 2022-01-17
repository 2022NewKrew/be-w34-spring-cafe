package com.kakao.cafe.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCreationForm {

    @Email
    private final String email;

    @NotBlank
    private final String username;

    @NotBlank
    private final String displayName;

    @NotBlank
    private final String password;


    public UserCreationForm(String email, String username, String password, String displayName) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }
}
