package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleUserInfo {
    private final String email;
    private final String nickName;
    private final String joinDate;
}
