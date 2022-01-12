package com.kakao.cafe.domain.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Users {
    private List<User> users = new ArrayList<User>();
    private int maxIndex = 0;

    public void addUser(User user){
        try{
            findByStringId(user.getStringId());
            return;
        }
        catch (IllegalArgumentException e){
            user.setId(maxIndex);
            users.add(user);
            maxIndex++;
        }
    }

    public void updateUser(int id, User user){
        findById(id).update(user);
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public User findByStringId(String stringId){
        return users.stream().filter(user -> user.getStringId().equals(stringId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
