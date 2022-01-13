package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.exception.UserDuplicateException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (!userRepository.insert(user))
            throw new UserDuplicateException(user.getId());
    }

    public User findById(String id) throws UserNotFoundException {
        Optional<User> res = userRepository.findById(id);
        if (res.isEmpty())
            throw new UserNotFoundException(id);
        return res.get();
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
