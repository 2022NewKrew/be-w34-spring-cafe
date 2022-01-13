package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserMemoryRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.utility.ErrorCode;
import com.kakao.cafe.utility.UserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserMemoryRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(User user) {
        validateDuplicateUser(user.getUserId());
        userRepository.save(user);
    }

    private void validateDuplicateUser(String userId) {
        if (userRepository.findByUserId(userId).isPresent())
            throw new UserException(ErrorCode.DUPLICATE_USER_ID);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }
        return user.get();
    }
}
