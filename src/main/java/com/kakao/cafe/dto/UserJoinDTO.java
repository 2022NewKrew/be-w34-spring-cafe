package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinDTO {
    private String email;
    private String password;
    private String nickName;

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .build();
    }
}
