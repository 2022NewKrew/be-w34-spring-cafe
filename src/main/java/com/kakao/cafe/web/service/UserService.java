package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserService {
    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Map<String, String>> getUsers() {
        List<Map<String, String>> userList = new ArrayList<>();
        List<Users> queriedUsers = userRepository.selectAllUsers();
        for (int i = 0; i < queriedUsers.size(); i++) {
            Users user = queriedUsers.get(i);
            userList.add(
                    (Map.of("index", Integer.toString(i + 1),
                            "id", Integer.toString(user.getId()),
                            "userId", user.getUserId(),
                            "name", user.getName(),
                            "email", user.getEmail()))
            );
        }
        return userList;
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
