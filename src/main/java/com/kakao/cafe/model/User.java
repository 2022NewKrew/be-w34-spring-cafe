package com.kakao.cafe.model;

import com.kakao.cafe.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private Integer user_id;
    private String userId;
    private String password;
    private String userName;
    private String email;

    @Builder
    public User(int user_id, String userId, String password, String userName, String email) {
        this.user_id = user_id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public UserDto toDto() {
        return new UserDto(user_id, userId, password, userName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
