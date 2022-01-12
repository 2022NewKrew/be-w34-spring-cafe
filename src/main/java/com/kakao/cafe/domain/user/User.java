package com.kakao.cafe.domain.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private Long id;
    private String email;       // UNIQUE
    private String nickname;
    private String password;
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String email, String nickname, String password, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createdAt = createdAt;
    }

    // 데이터베이스를 사용하지 않고, 메모리에 데이터를 저장하는 방식이기 때문에 필요함.
    // 대신에 default 접근제어자를 통해 다른 패키지의 클래스는 사용할 수 없도록 함.
    // 데이터베이스와 연결하면 삭제 필요
    void setId(Long id) {
        this.id = id;
    }
}
