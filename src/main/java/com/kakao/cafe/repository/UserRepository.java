package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    static private List<User> users = new ArrayList<>(); //DB와 동기화될 리스트.

    //전체유저의 목록을 반환
    public List<User> getUsers() {
        return users;
    }

    //회원가입처리
    public void userAdd(User user){
        users.add(user);
    }
}
