package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;

import java.util.List;

public interface UserRepository {
    //전체유저의 목록을 반환
    public List<User> getUsers();

    //특정유저를 반환
    public User getUserByCondition(String key, String value);

    //회원가입처리
    public void addUser(UserCreateDTO userCreateDTO);
}
