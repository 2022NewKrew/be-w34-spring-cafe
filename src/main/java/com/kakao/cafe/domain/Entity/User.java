package com.kakao.cafe.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

}
