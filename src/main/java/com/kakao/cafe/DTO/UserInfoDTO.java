package com.kakao.cafe.DTO;

public class UserInfoDTO {
    private final int idx;
    private final String uID;
    private final String name;
    private final String email;

    public UserInfoDTO(int idx, String uID, String name, String email) {
        this.idx = idx;
        this.uID = uID;
        this.name = name;
        this.email = email;
    }

    public int getIdx() {
        return idx;
    }

    public String getUID() {
        return uID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
