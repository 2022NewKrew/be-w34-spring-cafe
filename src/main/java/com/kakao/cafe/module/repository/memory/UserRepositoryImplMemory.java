package com.kakao.cafe.module.repository.memory;

import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImplMemory implements UserRepository {

    private final List<User> userList = new ArrayList<>();

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userList;
    }

    @Override
    public User findUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    @Override
    public void updateUser(User user) {
        user.update(user.getPassword(), user.getName(), user.getEmail());
    }
}
