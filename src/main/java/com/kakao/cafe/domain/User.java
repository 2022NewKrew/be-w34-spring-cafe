package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserCreateRequest;
import lombok.*;

@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String name;
    private String email;

    public User(UserCreateRequest userCreateRequest) {
        this.nickname = userCreateRequest.getUserId();
        this.password = userCreateRequest.getPassword();
        this.name = userCreateRequest.getName();
        this.email = userCreateRequest.getEmail();
    }

    @Builder
    public User(Long id, String nickname, String password, String name, String email) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    //TODO 지울 수 있는방법 생각해보기
    public void setId(Long id) {
        this.id = id;
    }
}
