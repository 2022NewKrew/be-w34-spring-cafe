package com.kakao.cafe.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    private final Long id;

    @NotNull
    @Size(min = 1, max = 20)
    private final String userId;

    @NotNull
    @Size(min = 1, max = 20)
    private final String password;

    @Email
    @NotNull
    @Size(min = 1, max = 50)
    private final String email;

    private final String time;

    public UserDTO(Long id, String userId, String password, String email, String time) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getUserId() {
        return userId;
    }


    public String getTime() {
        return time;
    }

}
