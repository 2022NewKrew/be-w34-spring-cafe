package com.kakao.cafe.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<User> validatedUser = userRepository.validate(user.getId(), prevPassword);
        if (validatedUser.isPresent()){
            userRepository.update(user);
        }
    }

    public User loginUser(User user) {
        Optional<User> validatedUser = userRepository.validate(user.getId(), user.getPassword());
        return validatedUser.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 USER!"));
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserBySeq(long seq) {
        return userRepository.findBySeq(seq).orElseThrow();
    }

}
