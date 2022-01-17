package com.kakao.cafe.dto.user;

public class UserModifyCommand {
    private final String password;
    private final String name;
    private final String email;

    public UserModifyCommand(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
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

    @Override
    public String toString() {
        return "UserModifyCommand{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
