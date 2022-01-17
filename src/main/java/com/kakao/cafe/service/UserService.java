package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void insert(User user) {
        try {
            userRepository.insert(user);
        } catch (Exception e) {
            throw new UserDuplicateException(e, user.getId());
        }
    }

    public User findById(String id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(e, id);
        }
    }

    public void update(User newInfo, String oldPassword) throws InvalidPasswordException {
        User oldInfo = findById(newInfo.getId());
        if (oldInfo.isPassword(oldPassword))
            throw new InvalidPasswordException(oldPassword);
        userRepository.update(newInfo);
    }

    public String login(String id, String password) {
        User user = null;
        try {
            user = userRepository.findById(id);
        } catch (Exception exception) {
            throw new LoginFailException(exception, id);
        }
        if (user.isPassword(password))
            throw new LoginFailException(new InvalidPasswordException(password), id);
        return user.getId();
    }

    public void canUpdate(String user1, String user2) {
        if (!user1.equals(user2))
            throw new UnauthorizedAction(new RuntimeException(), String.format("'%s', '%s'", user1, user2));
    }

    public Users findAll() {
        return userRepository.findAll();
    }
}
