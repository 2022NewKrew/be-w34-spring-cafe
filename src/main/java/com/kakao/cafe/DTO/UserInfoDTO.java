package com.kakao.cafe.DTO;

public class UserInfoDTO {
    private final int idx;
    private final String userId;
    private final String name;
    private final String email;

    public UserInfoDTO(int idx, String userId, String name, String email) {
        this.idx = idx;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getIdx() {
        return idx;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
