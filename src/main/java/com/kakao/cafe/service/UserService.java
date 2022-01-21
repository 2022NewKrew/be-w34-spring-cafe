package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.user.*;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userId) { return userRepository.search(userId); }

    public UserInfo getUserInfo(String userId) {
        return new UserInfo(userRepository.search(userId));
    }

    public UserProfileInfo getUserProfile(String userId) {
        return new UserProfileInfo(userRepository.search(userId));
    }

    public List<User> getAllUsers() { return Collections.unmodifiableList(userRepository.getAllUser()); }

    public void createUser(UserCreateCommand ucc) {
        userRepository.store(ucc);
    }

    public void modifyUser(String userId, UserModifyCommand umc) {
        userRepository.modify(userId, umc);
    }
}
