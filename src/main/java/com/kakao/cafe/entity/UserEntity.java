package com.kakao.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {
    private Long id;
    private String email;
    private String nickName;
    private String password;

    public void putUserId(Long id) {
        this.id = id;
    }

    public void putCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void putUpdatedDate() {
        this.updatedDate = LocalDateTime.now();
    }
}
