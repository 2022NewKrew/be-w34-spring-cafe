package com.kakao.cafe.dto.user;

public class ModifyingUserForm {
    private final Long userId;
    private final String nickName;
    private final String password;
    private final String name;
    private final String email;

    public ModifyingUserForm(Long userId, String nickName, String password, String name, String email) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
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
