package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserMemoryRepository implements UserRepository{
    private final List<User> userList = new ArrayList<>();
    private static Integer seq = 0;

    public void save(User user){
        user.setUser_id(seq++);
        userList.add(user);
    }

    public List<User> findAll() {
        return userList;
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return Optional.empty();
    }

}
