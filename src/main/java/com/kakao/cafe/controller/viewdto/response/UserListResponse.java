package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.user.service.AllUsersResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class UserListResponse extends HashMap<String, Object> {
    public UserListResponse(AllUsersResponseDTO dto) {
        ArrayList<HashMap<String, Object>> users = new ArrayList<>();
        int index = dto.getAllUserDataList().size();

        for (AllUsersResponseDTO.OneUserDataDTO oneUserDataDTO : dto.getAllUserDataList()) {
            HashMap<String, Object> user = new HashMap<>();
            user.put("num", index--);
            user.put("stringid", oneUserDataDTO.getStringId());
            user.put("name", oneUserDataDTO.getNickName());
            user.put("email", oneUserDataDTO.getEmail());
            users.add(user);
        }
        this.put("users", users);
    }
}
