package com.kakao.cafe.domain;

import com.kakao.cafe.service.user.dto.UserUpdateForm;
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

    public User(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public User(Long id, String userId, String password, String userName, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public static User of(String userId, String password, String userName, String email) {
        return new User(userId, password, userName, email);
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
