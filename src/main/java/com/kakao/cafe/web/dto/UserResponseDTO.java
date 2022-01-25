package com.kakao.cafe.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final long id;
    private final String password;
    private final String userId;
    private final String email;
    private final String registerDate;

    @Builder
    public UserResponseDTO(long id, String password, String userId, String email, String registerDate) {
        this.id = id;
        this.password = password;
        this.userId = userId;
        this.email = email;
        this.registerDate = registerDate;
    }

    public static UserResponseDTO nonePasswordInstance(UserResponseDTO userResponseDTO) {
        return UserResponseDTO.builder()
                .userId(userResponseDTO.getUserId())
                .email(userResponseDTO.getEmail())
                .registerDate(userResponseDTO.getRegisterDate())
                .build();
    }
}
