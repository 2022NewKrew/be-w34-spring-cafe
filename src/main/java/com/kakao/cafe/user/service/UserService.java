package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(User user) throws IllegalArgumentException {
        user.validate();
        validateDuplicatedUserId(user.getUserId());
        userRepository.createUser(user);
    }

    private void validateDuplicatedUserId(String userId) {
        if (userRepository.isUserIdUsed(userId)) {
            throw new IllegalArgumentException("[ERROR] 이미 사용중인 아이디입니다.");
        }
    }

    public Collection<User> getUsers() {
        return userRepository.readUserList();
    }

    public User getUserByUserId(String userId) {
        if (!userRepository.isUserIdUsed(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 사용자를 찾을 수 없습니다.");
        }
        return userRepository.readByUserId(userId);
    }

    public User updateUser(String userId, String password, String name, String email) {
        final User user = getUserByUserId(userId);
        user.validateAndSetPassword(password);
        user.setName(name);
        user.setEmail(email);
        return userRepository.updateUser(user);
    }
}
