package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {

    private final List<User> users = new ArrayList<>();

    public void addUser(UserDto userDto) {
        users.add(new User(users.size() + 1, userDto));
    }

    public User findById(int id) {
        return users.stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public void updateUser(int id, UserDto userDto) {
        User user = findById(id);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
    }

    public int size(){
        return users.size();
    }
}
