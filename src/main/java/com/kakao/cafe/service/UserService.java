package com.kakao.cafe.service;

import com.kakao.cafe.domain.SessionUser;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exceptions.WrongPasswordException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.request.LoginRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId());
        if (!user.getPassword().equals(request.getPassword())) {
            throw new WrongPasswordException("비밀번호가 잘못되었습니다");
        }
        return SessionUser.from(user);
    }
}
