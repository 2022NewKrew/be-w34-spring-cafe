package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity {
    private String email;
    private String username;
    private String password;

    @Override
    public LocalDateTime getRegDate() {
        return super.getRegDate();
    }

    @Override
    public LocalDateTime getModDate() {
        return super.getModDate();
    }

    public User init() {
        super.register();
        return this;
    }
}
