package com.kakao.cafe.dto;

import com.kakao.cafe.entity.User;
import org.springframework.lang.NonNull;

public class UserDto {
    private long idx;
    private String id;
    private String name;
    private String email;

    static public UserDto from(@NonNull final User user) {
        UserDto userDto = new UserDto();
        userDto.setIdx(user.getIdx());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
