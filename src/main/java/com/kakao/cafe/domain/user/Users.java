package com.kakao.cafe.domain.user;

import com.kakao.cafe.dto.UserSaveDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<User>();
    private int maxIndex = 0;

    public void addUser(UserSaveDto userSaveDto){
        users.add(new User(maxIndex, userSaveDto));
        maxIndex++;
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
