package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<>();

    public void addUser(UserDto userDto) {
        users.add(new User(users.size() + 1, userDto));
    }

    public User findById(int id) {
        return users.stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public int size(){
        return users.size();
    }
}
