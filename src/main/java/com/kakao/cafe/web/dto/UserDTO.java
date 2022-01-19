package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class UserDTO {

    private final long id;
    private final String password;
    private final String userId;
    private final String email;
    private final String registerDate;

    public UserDTO(String userId, String password, String email) {
        this.id = 0;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = LocalDate.now().toString();
    }

    private UserDTO(User user) {
        this.id = user.getId();
        this.password = "";
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.registerDate = user.getRegisterDate();
    }

    public static UserDTO newInstance(User user) {
        return new UserDTO(user);
    }

}
