package com.kakao.cafe.domain;


import com.kakao.cafe.controller.dto.UserJoinForm;
import com.kakao.cafe.repository.dto.UserResult;
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

    public static User from(UserJoinForm dto) {
        return new User()
                .setUserId(dto.getUserId())
                .setEmail(dto.getEmail())
                .setName(dto.getName())
                .setPassword(dto.getPassword());
    }

    public static User from(UserResult dto) {
        return new User()
                .setUserId(dto.getUserId())
                .setEmail(dto.getEmail())
                .setName(dto.getName())
                .setPassword(dto.getPassword());
    }


    public boolean chcekPassword(String password) {
        return this.password.equals(password);
    }

    public void updateEmailAndName(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
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
