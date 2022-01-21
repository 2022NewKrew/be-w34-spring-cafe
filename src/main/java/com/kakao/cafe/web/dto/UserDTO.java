package com.kakao.cafe.web.dto;

import lombok.Builder;
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

    @Builder
    private UserDTO(long id, String userId, String password, String email, String registerDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }

    private UserDTO(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.password = userDTO.getPassword();
        this.userId = userDTO.getUserId();
        this.email = userDTO.getEmail();
        this.registerDate = userDTO.getRegisterDate();
    }

    public static UserDTO newInstance(String userId, String password, String email) {
        return UserDTO.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .registerDate(LocalDate.now().toString())
                .build();
    }

    public static UserDTO newInstanceNonePasswordInfo(UserDTO userDTO) {
        return UserDTO.builder()
                .userId(userDTO.getUserId())
                .email(userDTO.getEmail())
                .registerDate(userDTO.getRegisterDate())
                .build();
    }
}
