package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    public User findById(String userId) {
        return userRepository.findById(userId);
    }

    public User isvalidateLogin(User user) {
        User findUser = userRepository.findById(user.getUserId());
        if(!findUser.isValidateLogin(user)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return findUser;
    }
}
