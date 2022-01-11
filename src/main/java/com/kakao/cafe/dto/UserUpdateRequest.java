package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String nickname;
    private String originPassword;
    private String newPassword;
    private String name;
    private String email;


}
