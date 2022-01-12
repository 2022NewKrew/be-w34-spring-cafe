package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(String userId, String password, User user) {
        validCorrectPassword(userRepository.findById(userId), password);
        userRepository.update(userId, password, user);
    }

    private void validCorrectPassword(User user, String password) {
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password Incorrect : " + user.getUserId());
        }
    }

    @Transactional(readOnly = true)
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
