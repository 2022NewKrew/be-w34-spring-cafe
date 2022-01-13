package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class UserListDto {
    private final long index;
    private final String id;
    private final String name;
    private final String email;

    public UserListDto(long index, String id, String name, String email) {
        validate(index, id, name, email);
        this.index = index;
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private void validate(long index, String id, String name, String email) {

    }
}
