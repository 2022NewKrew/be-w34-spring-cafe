package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(User user) throws IllegalArgumentException {
        user.validate();
        validateDuplicatedUsername(user.getUsername());
        userRepository.createUser(user);
    }

    private void validateDuplicatedUsername(String username) {
        if (userRepository.isUsernameUsed(username)) {
            throw new IllegalArgumentException("[ERROR] 이미 사용중인 아이디입니다.");
        }
    }

    public List<User> getUsers() {
        return userRepository.findAllUsers();
    }

    public User getUserByUsername(String username) {
        if (!userRepository.isUsernameUsed(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 사용자를 찾을 수 없습니다.");
        }
        return userRepository.findByUsername(username);
    }

    public User updateUser(String username, User user) {
        user.validate();
        if (!username.equals(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "[ERROR] 본인의 정보만 수정할 수 있습니다.");
        }
        if (!userRepository.isUsernameUsed(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 사용자를 찾을 수 없습니다.");
        }
        return userRepository.updateUser(user);
    }
}
