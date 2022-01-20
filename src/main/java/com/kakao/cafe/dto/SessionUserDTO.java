package com.kakao.cafe.dto;

public class SessionUserDTO extends UserDTO {
    public SessionUserDTO(UserDTO other) {
        super(other.getId(), other.getUserId(), other.getPassword(), other.getEmail(), other.getTime());
    }
}
