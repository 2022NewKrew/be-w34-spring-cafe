package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserListDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final UserTransformation userTransformation = new UserTransformation();
    public List<UserListDto> getUserList() {
        List<UserDao> userDaoList = userRepository.selectAll();
        return userDaoList.stream().map(userTransformation::toUserListDto).collect(Collectors.toList());
    }

    public void createUser(User user) {
        userRepository.insert(user);
    }

    public UserProfileDto readUserProfileDto(String id) {
        return userTransformation.toUserProfileDto(userRepository.select(id));
    }
}
