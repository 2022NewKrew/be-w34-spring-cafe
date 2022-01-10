package com.kakao.cafe.service;

import com.kakao.cafe.entiry.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        userRepository.finadByUserId(user.getUserId())
                .ifPresent(u -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId){
        Optional<User> user = userRepository.finadByUserId(userId);
        if (user.isEmpty()) throw new IllegalArgumentException("일치하는 아이디가 없습니다.");
        return user.get();
    }
}
