package com.kakao.cafe.dto;

public class UserProfileResponse {

    private final int id;
    private final String userId;
    private final String name;
    private final String email;

    public UserProfileResponse(int id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }


}
