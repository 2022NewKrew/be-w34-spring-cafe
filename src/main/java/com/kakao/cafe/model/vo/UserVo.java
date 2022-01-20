package com.kakao.cafe.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserVo(int id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
}
