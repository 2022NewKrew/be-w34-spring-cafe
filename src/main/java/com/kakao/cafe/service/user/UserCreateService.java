package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserCreateService {

    private final UserRepository userRepository;

    public UserCreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(User user) {
        validPresentUserCheck(user);
        userRepository.save(user);
    }

    private void validPresentUserCheck(User user) {
        Optional.ofNullable(userRepository.findById(user.getUserId())).ifPresent(m -> {
            throw new IllegalArgumentException("The user already exist : " + user.getUserId());
        });
    }
}
