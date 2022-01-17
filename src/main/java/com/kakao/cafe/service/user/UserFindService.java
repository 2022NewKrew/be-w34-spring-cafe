package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserFindService {

    private final UserRepository userRepository;

    public UserFindService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findById(UserId id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
