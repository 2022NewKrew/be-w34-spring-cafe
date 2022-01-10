package com.kakao.cafe.dto.request;

public class SignupReqDto {
    private String id;
    private String password;
    private String name;
    private String email;

    public SignupReqDto(String id, String password, String name, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
