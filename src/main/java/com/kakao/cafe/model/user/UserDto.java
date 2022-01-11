package com.kakao.cafe.model.user;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDateTime createdAt;

    public String formattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
