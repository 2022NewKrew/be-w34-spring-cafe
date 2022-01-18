package com.kakao.cafe.user.domain;


import com.kakao.cafe.user.dto.UpdateDTO;
import com.kakao.cafe.user.factory.UserFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(Long id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }


    public User updateInfo(UpdateDTO updateDTO) {
        return UserFactory.toUser(updateDTO);
    }

    public boolean equalsPassword(String InputPassword) {
        return this.password.equals(InputPassword);
    }
}
