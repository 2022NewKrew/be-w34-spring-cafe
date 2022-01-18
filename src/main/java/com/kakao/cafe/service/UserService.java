package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
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

    public void signup(User targetUser) throws IllegalArgumentException {
        targetUser.validate();
        validateDuplicatedUserId(targetUser.getUserId());
        userRepository.createUser(targetUser);
    }

    private void validateDuplicatedUserId(String targetUserId) throws IllegalArgumentException {
        if (userRepository.isUserIdUsed(targetUserId)) {
            throw new IllegalArgumentException("[ERROR] 이미 사용중인 아이디입니다.");
        }
    }

    public Collection<User> getUsers() {
        return userRepository.readUserList();
    }

    public User getUserFromUserId(String targetUserId) throws ResponseStatusException{
        if (userRepository.isUserIdUsed(targetUserId)) {
            return userRepository.readByUserId(targetUserId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 사용자를 찾을 수 없습니다.");
        }
    }
}
