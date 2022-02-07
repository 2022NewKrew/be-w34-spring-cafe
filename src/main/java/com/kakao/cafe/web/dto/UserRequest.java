package com.kakao.cafe.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class UserRequest {

    private final long id;
    private final String password;
    private final String userId;
    private final String email;
    private final String registerDate;

    @Builder
    private UserRequest(long id, String userId, String password, String email, String registerDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }

    public static UserRequest newInstance(String userId, String password, String email) {
        return UserRequest.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .registerDate(LocalDate.now().toString())
                .build();
    }
}
