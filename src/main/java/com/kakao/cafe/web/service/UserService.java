package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers() {
        return userRepository.selectAllUsers();
    }

    public Users addUser(Users user) {
        return userRepository.insertUser(user);
    }

    public Users getByUserId(int id) {
        return userRepository.selectByUserId(id);
    }

    public Users getByUserName(String userId) {
        return userRepository.selectByUserName(userId);
    }

    public void updateUser(int id, Users updateUser, String newPassword) {
        if (!getByUserId(id).getPassword().equals((updateUser.getPassword())))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        if (!newPassword.isBlank())
            updateUser.setPassword(newPassword);
        userRepository.updateUser(id, updateUser);
    }

}
