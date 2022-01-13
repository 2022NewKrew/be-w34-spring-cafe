package com.kakao.cafe.domain.user;

import com.kakao.cafe.web.dto.user.UserResponseDto;
import com.kakao.cafe.web.dto.user.UsersListResponseDto;
import com.kakao.cafe.web.dto.user.UsersSaveRequestDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<>();
    private int maxId = 0;

    public void addUser(UsersSaveRequestDto userDto){
        users.add(new User(maxId++, userDto));
    }

    public UsersListResponseDto findAll() {
        return new UsersListResponseDto(users);
    }

    public UserResponseDto findUserById(int id) {
        return new UserResponseDto(users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException()));
    }
}
