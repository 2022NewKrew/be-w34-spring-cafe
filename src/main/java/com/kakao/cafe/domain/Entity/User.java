package com.kakao.cafe.domain.Entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

}
