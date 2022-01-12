package com.kakao.cafe.interfaces.user.dto.request;

public class UpdateUserRequestDto {
    private final String prePassword;
    private final String changePassword;
    private final String name;
    private final String email;

    public UpdateUserRequestDto(String prePassword, String changePassword, String name, String email) {
        this.prePassword = prePassword;
        this.changePassword = changePassword;
        this.name = name;
        this.email = email;
    }

    public String getPrePassword() {
        return prePassword;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
