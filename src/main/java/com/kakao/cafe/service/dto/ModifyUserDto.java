package com.kakao.cafe.service.dto;

import com.kakao.cafe.domain.entity.ModifyUser;

public class ModifyUserDto {

    private final String password;
    private final String name;
    private final String email;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ModifyUserDto(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public ModifyUser toEntity() {
        return new ModifyUser(name, email);
    }
}
