package com.kakao.cafe.DTO;

public class SignInDTO {
    private final String uID;
    private final String password;
    private final String name;
    private final String email;

    public SignInDTO(String uID, String password, String name, String email) {
        this.uID = uID;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUID() {
        return uID;
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
