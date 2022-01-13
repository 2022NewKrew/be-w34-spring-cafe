package com.kakao.cafe.domain.user;

import com.kakao.cafe.dto.user.UserReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

}
