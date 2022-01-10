package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final List<User> userList = new ArrayList<>();

    public void addUser(User user){
        userList.add(user);
    }

    public List<User> findAllUsers(){
        return userList;
    }

    public User findUser(Long sn){
        return userList.stream()
                .filter(user -> user.getSn().equals(sn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
