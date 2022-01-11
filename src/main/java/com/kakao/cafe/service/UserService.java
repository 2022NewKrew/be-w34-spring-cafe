package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreateRequest;
import com.kakao.cafe.repository.InMemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository = new InMemoryUserRepository();

    public Long signUp(UserCreateRequest userDTO) {
        User user = new User(userDTO);
        validateDuplicateUserId(user);
        return userRepository.save(user).getId();
    }

    private void validateDuplicateUserId(User user){
        userRepository.findByUserId(user.getNickname())
                .ifPresent(u -> {
                    throw new IllegalStateException("가입할 수 없는 ID입니다.");
                });
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findOneUser(String userId) {
        return userRepository.findByUserId(userId).get();
    }
    public User findOneUser(Long id) {
        return userRepository.findById(id).get();
    }
}
