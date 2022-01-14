package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.user.*;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo getUser(String userId) {
        return userRepository.getUserInfo(userId);
    }

    public UserProfileInfo getUserProfile(String userId) {
        return userRepository.getUserProfile(userId);
    }

    public List<UserListShow> getAllUsers() {
        return userRepository.getAllUser();
    }

    public void createUser(UserCreateCommand ucc) {
        userRepository.store(ucc);
    }

    public void modifyUser(String userId, UserModifyCommand umc) {
        userRepository.modify(userId, umc);
    }
}
