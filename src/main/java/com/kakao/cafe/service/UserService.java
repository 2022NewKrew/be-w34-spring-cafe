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
        userRepository.findByUserId(user.getUserId())
                .ifPresent(u -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(() -> {throw new IllegalArgumentException("일치하는 아이디가 없습니다.");});
    }

    public User updateUser(User user) {
        User findUser = findByUserId(user.getUserId());
        checkPassword(user, findUser);
        user.setId(findUser.getId());
        return userRepository.update(user);
    }

    private void checkPassword(User user, User findUser) {
        if (!user.getPassword().equals(findUser.getPassword()))
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }
}
