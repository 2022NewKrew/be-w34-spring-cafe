package com.kakao.cafe.controller.viewdto;

import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;

import java.util.*;

public class UserControllerResponseMapper {
    public static List<Map<String, Object>> getUserListResponse(AllUserProfileServiceResponse dto) {
        List<Map<String, Object>> users = new ArrayList<>();
        int index = dto.getAllUserProfileList().size();

        for (UserProfileServiceResponse res : dto.getAllUserProfileList()) {
            HashMap<String, Object> user = new HashMap<>();
            user.put("num", index--);
            user.put("stringid", res.getStringId());
            user.put("name", res.getName());
            user.put("email", res.getEmail());
            users.add(user);
        }
        return users;
    }
}
