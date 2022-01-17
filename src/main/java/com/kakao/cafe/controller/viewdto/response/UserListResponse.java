package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class UserListResponse extends HashMap<String, Object> {
    public UserListResponse(AllUserProfileServiceResponse dto) {
        ArrayList<HashMap<String, Object>> users = new ArrayList<>();
        int index = dto.getAllUserProfileList().size();

        for (UserProfileServiceResponse res : dto.getAllUserProfileList()) {
            HashMap<String, Object> user = new HashMap<>();
            user.put("num", index--);
            user.put("stringid", res.getStringId());
            user.put("name", res.getName());
            user.put("email", res.getEmail());
            users.add(user);
        }
        this.put("users", users);
    }
}
