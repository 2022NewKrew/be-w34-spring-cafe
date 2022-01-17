package com.kakao.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserEntity extends BaseEntity {
    private Long id;
    private String email;
    private String nickName;
    private String password;
    private LocalDateTime registeredDate;

    public void putUserId(Long id) {
        this.id = id;
    }
}
