package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserMemoryRepository implements UserRepository {

    private static List<User> userList = new ArrayList<>();

    @Override
    public User save(SignUpDTO signUpDTO) {
        User user = new User(userList.size() + 1, signUpDTO);
        userList.add(user);
        return user;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return Collections.unmodifiableList(userList);
    }
}
