package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.user.service.UserProfileResponseDTO;

import java.util.HashMap;

public class UserProfileResponse extends HashMap<String, Object> {
    public UserProfileResponse(UserProfileResponseDTO dto) {
        this.put("stringId", dto.getStringId());

        this.put("name", dto.getNickName());
        this.put("email", dto.getEmail());
    }
}
