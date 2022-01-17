package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.dto.user.UserUpdateRequestDto;
import com.kakao.cafe.dto.user.UsersListResponseDto;
import com.kakao.cafe.dto.user.UsersSaveRequestDto;
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
        return new UserResponseDto(findById(id));
    }

    private User findById(int id) {
        return users.stream().filter(user -> user.getId()==id).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public void update(int id, UserUpdateRequestDto userDto) {
        User target = findById(id);
        if (target.getAccPw().equals(userDto.getPrevAccPw())) {
            target.setAccPw(userDto.getNewAccPw());
            target.setName(userDto.getName());
            target.setEmail(userDto.getEmail());
        }
    }
}
