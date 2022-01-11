package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDtoForList {
    private String email;
    private String nickName;
    private String joinDate;
}
