package com.kakao.cafe.user.service;

import com.kakao.cafe.user.dto.UserRegistrationDto;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.repo.UserRepository;
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

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public long create(@Valid User user) {
        String hashedPassword = passwordEncoder.encode(user.getPlainPassword());
        user.setHashedPassword(hashedPassword);
        user.setPlainPassword(null);
        return repository.save(user);
    }

    public void create(UserRegistrationDto dto) {
        User user = new User(dto.getUserId(), dto.getPassword(), dto.getName(), dto.getEmail());
        create(user);
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
