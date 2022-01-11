package com.kakao.cafe.domain.user;

import com.kakao.cafe.web.dto.UsersSaveRequestDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<User>();
    private int maxId = 0;

    public void addUser(UsersSaveRequestDto userDto){
        users.add(new User(maxId++, userDto));
    }

    public User findUserById(int id){
        return users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
