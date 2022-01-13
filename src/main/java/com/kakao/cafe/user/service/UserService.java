package com.kakao.cafe.user.service;

import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void create(User user) {
        repository.save(user);
    }

    public List<User> fetchAll() {
        return repository.fetchAll();
    }

    public int getUserCount() {
        return repository.getUserCount();
    }

    public Optional<User> fetchByUserId(String userId) {
        return repository.fetchByUserId(userId);
    }
}
