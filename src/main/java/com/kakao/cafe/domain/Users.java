package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserSaveDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<User>();

    public void addUser(UserSaveDto userSaveDto){
        users.add(new User(users.size(), userSaveDto));
    }
}
