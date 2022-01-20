package com.kakao.cafe.service;

import com.kakao.cafe.model.User;

import java.util.List;

public interface UserService {
    public User createUser(String id, String password, String name, String email, String age);
    public void addUser(User user);
    public List<User> readUsers();
    public User readUser(String user);
    public void updateUser(String userId, User user);
    public void deleteUser(String userId);
}
