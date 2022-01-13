package com.kakao.cafe.user.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserProfileResponseDTO {
    public String nickName;
    public String email;
    public String stringId;
}
