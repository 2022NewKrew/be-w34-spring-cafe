package com.kakao.cafe.domain;


import com.kakao.cafe.controller.dto.UserJoinDto;
import com.kakao.cafe.repository.dto.UserResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {
    private String userId;
    private String name;
    private String email;
    private String password;

    private User() {}
    
    public static User from(UserJoinDto dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static User from(UserResult dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }


    public boolean chcekPassword(String password) {
        return this.password.equals(password);
    }

    public void updateEmailAndName(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.email, this.password, this.userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User)obj;

        return Objects.equals(name, user.getName()) &&
                Objects.equals(userId, user.getUserId()) &&
                Objects.equals(email, user.getEmail()) &&
                Objects.equals(password, user.getPassword());
    }
}
