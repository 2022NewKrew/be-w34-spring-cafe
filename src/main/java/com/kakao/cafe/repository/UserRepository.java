package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    static private List<User> users = new ArrayList<>(); //DB와 동기화될 리스트.

    //회원가입처리
    public void userAdd(User user){
        users.add(user);
    }
}
