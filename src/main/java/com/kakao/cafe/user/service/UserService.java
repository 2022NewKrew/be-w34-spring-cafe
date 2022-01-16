package com.kakao.cafe.user.service;

import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(@Valid User user) {
        String hashedPassword = passwordEncoder.encode(user.getPlainPassword());
        user.setHashedPassword(hashedPassword);
        user.setPlainPassword(null);
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
