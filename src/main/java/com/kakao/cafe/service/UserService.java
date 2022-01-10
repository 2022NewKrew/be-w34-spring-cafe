package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.NoSuchUserException;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User findUserById(String userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new NoSuchUserException("해당 아이디의 사용자를 찾을 수 없습니다."));
    }
}
