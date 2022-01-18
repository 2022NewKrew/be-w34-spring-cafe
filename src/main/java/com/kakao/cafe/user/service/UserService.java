package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.domain.UserValidator;
import com.kakao.cafe.user.dto.LoginRequest;
import com.kakao.cafe.user.dto.Profile;
import com.kakao.cafe.user.dto.SessionUser;
import com.kakao.cafe.user.exception.UserNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Transactional
    public void signup(User user) {
        user.signup(userValidator);
        userRepository.save(user);
    }

    public List<Profile> getAllUsers() {
        return userRepository.findAll();
    }

    public Profile getProfileById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(UserNotFoundException::new);
        user.login(userValidator, request.getPassword());
        return SessionUser.from(user);
    }
}
