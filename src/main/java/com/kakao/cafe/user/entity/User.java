package com.kakao.cafe.user.entity;

import com.kakao.cafe.user.dto.request.UserCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class User {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public User(UserCreateRequest req) {
        this(req.getUserId(),
             req.getPassword(),
             req.getName(),
             req.getEmail());
    }

    public User(String userId, String password, String name, String email) {
        this(null, userId, password, name, email, null);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
