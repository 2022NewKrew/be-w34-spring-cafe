package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserCreateService {

    private final UserRepository userRepository;

    @Autowired
    public UserCreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        validPresentUserCheck(user);
        userRepository.save(user);
    }

    private void validPresentUserCheck(User user) {
        Optional.ofNullable(userRepository.findById(user.getUserId())).ifPresent(m -> {
            throw new IllegalArgumentException("The user already exist : " + user.getUserId());
        });
    }
}
