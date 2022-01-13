package com.kakao.cafe.domain;

import com.kakao.cafe.service.user.dto.UserUpdateForm;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class User {
    private Long id;
    private String userId;
    private String password;
    private String userName;
    private String email;

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
        this.userId = userUpdateForm.getUserId();
        boolean isUpdatePassword = !userUpdateForm.getPassword().equals("");
        if(isUpdatePassword) {
            this.password = userUpdateForm.getPassword();
        }
        this.userName = userUpdateForm.getUserName();
        this.email = userUpdateForm.getEmail();
    }
}
