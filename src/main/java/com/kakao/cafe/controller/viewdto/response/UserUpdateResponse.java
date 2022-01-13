package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.user.service.UserProfileResponseDTO;

import java.util.HashMap;

public class UserUpdateResponse extends HashMap<String, Object> {

    public UserUpdateResponse(UserProfileResponseDTO dto) {
        this.put("name", dto.getNickName());
        this.put("email", dto.getEmail());
        this.put("stringId", dto.getStringId());
    }
}
