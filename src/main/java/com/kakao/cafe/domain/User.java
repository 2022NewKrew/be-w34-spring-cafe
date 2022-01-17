package com.kakao.cafe.domain;

import com.kakao.cafe.service.user.dto.UserUpdateForm;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private Long id;
    private String userId;
    private String password;
    private String userName;
    private String email;

    @Builder
    public User(Long id, String userId, String password, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public static User of(String userId, String password, String userName, String email) {
        return User.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .email(email).build();
    }

    public void updateId(Long id) {
        this.id = id;
    }

    public void update(UserUpdateForm userUpdateForm) {
        this.userName = userUpdateForm.getUserName();
        this.email = userUpdateForm.getEmail();
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}
