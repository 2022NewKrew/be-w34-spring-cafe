package com.kakao.cafe.domain.user;

import com.kakao.cafe.web.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class User {
    private final long id;
    private final String userId;
    private final String password;
    private final String email;
    private final String registerDate;

    public User(UserDTO userDTO) {
        this.id = 0;
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.registerDate = userDTO.getRegisterDate();
    }

    private User(long id, String userId, String password, String email, String registerDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }

    public static User newInstance(long id, String userId, String password, String email, String registerDate) {
        return new User(id, userId, password, email, registerDate);
    }

    public boolean isPasswordMatching(String password) {
        return this.password.equals(password);
    }
}
