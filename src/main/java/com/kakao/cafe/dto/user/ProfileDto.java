package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {
    private final Long id;
    private final String nickName;
    private String password;
}
