package com.kakao.cafe.repository.memory;

import com.kakao.cafe.dto.user.UserCreateCommand;
import com.kakao.cafe.dto.user.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserMemoryRepository implements UserRepository {
    private final List<User> repository;

    public UserMemoryRepository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void store(UserCreateCommand ucc) {
        User user = new User(ucc.getUserId(), ucc.getPassword(), ucc.getName(), ucc.getEmail());
        this.repository.add(user);
    }

    @Override
    public void modify(String userId, UserModifyCommand umc) {
        User user = new User(userId, umc.getPassword(), umc.getName(), umc.getEmail());
        repository.set(findByName(user.getUserId()), user);
    }

    @Override
    public User search(String userId) {
        return this.repository.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public List<User> getAllUser() {
        return Collections.unmodifiableList(this.repository);
    }

    private int findByName(String name) {
        User target = search(name);
        return this.repository.indexOf(target);
    }

    private Long nextIndex() {
        return (long) this.repository.size() + 1;
    }
}
