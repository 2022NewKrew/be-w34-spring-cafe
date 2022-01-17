package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.exception.NoSuchUserException;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User findUserByUserName(UserName userName) {
        return userRepository.findUserByName(userName)
                .orElseThrow(() -> new NoSuchUserException("해당 아이디의 사용자를 찾을 수 없습니다."));
    }

    public User findUserById(UUID id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new NoSuchUserException("해당 아이디의 사용자를 찾을 수 없습니다."));
    }

    public User findUserByLoginInfo(UserName userName, Password password) {
        return userRepository.findUserByUserNameAndPassword(userName, password)
                .orElseThrow(() -> new LoginFailedException("로그인 정보가 올바르지 않습니다."));
    }
}
