package com.kakao.cafe.domain.users;

import com.kakao.cafe.domain.BaseTimeEntity;
import lombok.Getter;

@Getter
public class UserEntity extends BaseTimeEntity {
    private static int idCnt = 0;
    private final int id;
    private final String name;
    private final String email;
    private final String password;

    public UserEntity(String name, String email, String password) {
        super();
        this.id = idCnt++;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
