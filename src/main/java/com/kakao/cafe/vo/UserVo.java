package com.kakao.cafe.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private String userId;
    private String password;
    private String name;
    private String email;
}
