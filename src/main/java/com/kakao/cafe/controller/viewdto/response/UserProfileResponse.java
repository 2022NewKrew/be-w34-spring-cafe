package com.kakao.cafe.controller.viewdto.response;

import java.util.HashMap;

public class UserProfileResponse extends HashMap<String, Object> {

    public UserProfileResponse(String stringId, String name, String email){
        this.put("stringId", stringId);
        this.put("name", name);
        this.put("email", email);
    }

}
