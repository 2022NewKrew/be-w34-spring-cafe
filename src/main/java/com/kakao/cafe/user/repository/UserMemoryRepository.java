package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserMemoryRepository implements UserRepository{
    private List<User> users; //DB와 동기화될 리스트.

    public UserMemoryRepository(){
        users = new ArrayList<>();
    }

    //전체유저의 목록을 반환
    public List<User> getUsers() {
        return users;
    }

    //회원가입처리
    public void addUser(User user){
        users.add(user);
    }
}
