package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository {
    //전체유저의 목록을 반환
    public List<User> getUsers();

    //회원가입처리
    public void addUser(User user);
}
