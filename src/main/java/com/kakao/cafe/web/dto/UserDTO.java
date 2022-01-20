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
        this.password = user.getPassword();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.registerDate = user.getRegisterDate();
    }

    private UserDTO(UserDTO userDTO){
        this.id = userDTO.getId();
        this.password = userDTO.getPassword();
        this.userId = userDTO.getUserId();
        this.email = userDTO.getEmail();
        this.registerDate = userDTO.getRegisterDate();
    }

    public static UserDTO newInstance(User user) {
        return new UserDTO(user);
    }

    public static UserDTO newInstanceNonPasswordInfo(UserDTO userDTO){
        return new UserDTO(userDTO);
    }
}
