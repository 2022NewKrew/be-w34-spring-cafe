package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;

public class UserListDTO {
    private String userId;
    private String name;
    private String email;
    private Long sequence;

    public UserListDTO(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.sequence = user.getSequence();
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

    public Long getSequence() {
        return sequence;
    }
}
