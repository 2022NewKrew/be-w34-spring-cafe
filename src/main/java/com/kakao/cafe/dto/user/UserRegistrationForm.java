package com.kakao.cafe.dto.user;

public class UserRegistrationForm {
    private final String nickName;
    private final String email;
    private final String name;
    private final String password;

    public UserRegistrationForm(String nickName, String email, String name, String password) {
        this.nickName = nickName;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
