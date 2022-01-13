package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.exception.UserDuplicateException;
import com.kakao.cafe.util.exception.UserNotFoundException;
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

    public boolean update(User newInfo, String oldPassword) {
        User oldInfo = findById(newInfo.getId());
        if (!oldInfo.canModify(oldPassword))
            return false;
        userRepository.update(newInfo);
        return true;
    }

    public Users findAll() {
        return userRepository.findAll();
    }
}
