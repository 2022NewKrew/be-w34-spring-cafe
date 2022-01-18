package com.kakao.cafe.domain;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class User {
    /* UserRepository 저장시 pk 위해서 id 변수 생성*/
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String time;


}
