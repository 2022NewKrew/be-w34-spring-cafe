package com.kakao.cafe.domain.user.dao;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserFindDao {

    private final UserRepository userRepository;

    public UserFindDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(final Long id) {
        final Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException(id));
        return user.get();
    }

    public User findByUserId(final String userId) {
        final Optional<User> user = userRepository.findByUserId(userId);
        user.orElseThrow(() -> new UserNotFoundException(userId));
        return user.get();
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
