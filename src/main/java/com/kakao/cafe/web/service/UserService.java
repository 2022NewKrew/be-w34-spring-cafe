package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByUserId(user.getUserId()).
                ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public User authenticate(String userId, String password) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
        User loginUser = user.get();
        if (!Objects.equals(loginUser.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return loginUser;
    }

    @Transactional
    public void update(User newUser, String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }
        User updateUser = user.get();
        updateUser.setEmail(newUser.getEmail());
        updateUser.setName(newUser.getName());
        updateUser.setPassword(newUser.getPassword());
        userRepository.update(updateUser);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
