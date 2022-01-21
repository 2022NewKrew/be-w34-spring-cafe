package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.InvalidUsernamePasswordException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserLoginForm;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoggedInUser login(UserLoginForm userLoginForm) {
        User user = userRepository.getByUsername(userLoginForm.getUsername()).orElseThrow(
                InvalidUsernamePasswordException::new);
        if (user.isPasswordMatched(userLoginForm.getPassword())) {
            return new LoggedInUser(user.getId(), user.getUsername());
        }
        throw new InvalidUsernamePasswordException();
    }
}
