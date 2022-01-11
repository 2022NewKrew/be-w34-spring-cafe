package com.kakao.cafe.domain.user;

import com.kakao.cafe.dto.UserRequestDto;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<User>();
    private int maxIndex = 0;

    public void addUser(UserRequestDto userRequestDto){
        users.add(new User(maxIndex, userRequestDto));
        maxIndex++;
    }

    public void updateUser(int id, UserRequestDto userRequestDto){
        findById(id).update(userRequestDto);
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
