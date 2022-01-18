package com.kakao.cafe.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(String prevPassword, User user) {
        if (userRepository.validate(user.getId(), prevPassword)){
            userRepository.update(user);
        }
    }

    public boolean loginUser(User user) {
        if (userRepository.validate(user.getId(), user.getPassword())) {
            return true;
        }
        return false;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

}
