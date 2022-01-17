package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.user.*;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo getUser(String userId) {
        return new UserInfo(userRepository.search(userId));
    }

    public UserProfileInfo getUserProfile(String userId) {
        return new UserProfileInfo(userRepository.search(userId));
    }

    public List<UserListShow> getAllUsers() {
        return userRepository.getAllUser().stream()
                .map(UserListShow::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public void createUser(UserCreateCommand ucc) {
        userRepository.store(ucc);
    }

    public void modifyUser(String userId, UserModifyCommand umc) {
        userRepository.modify(userId, umc);
    }

    public User tryLogin(String userId, String password) {
        User target = userRepository.search(userId);

        if (target != null) {
            return target.getPassword().equals(password) ? target : null;
        }
        return null;
    }
}
