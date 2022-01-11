package com.kakao.cafe;

import com.kakao.cafe.dto.CreateUserDto;
import java.util.UUID;

public class User {

    private UUID user_id;
    private String nickname;
    private String password;
    private String email;

    public User(CreateUserDto createUserDto) {
        this.user_id = UUID.randomUUID();
        this.email = createUserDto.getEmail();
        this.nickname = createUserDto.getNickname();
        this.password = createUserDto.getPassword();
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
