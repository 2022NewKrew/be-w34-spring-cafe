package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserId(String userId) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return optionalUser.get();
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }


    @Override
    public void join(User user) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.findByUserId(user.getUserId());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException();
        }

        userRepository.save(user);
    }

}
