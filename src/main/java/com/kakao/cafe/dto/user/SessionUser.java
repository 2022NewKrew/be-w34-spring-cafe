package com.kakao.cafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SessionUser {
    Long id;
    String userId;
}
