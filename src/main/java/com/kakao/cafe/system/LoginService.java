package com.kakao.cafe.system;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by melodist
 * Date: 2022-01-17 017
 * Time: 오후 6:23
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(String userId, String password) {
        User loginUser = userRepository.findUserByUserId(userId);

        if (loginUser == null) {
            return null;
        }

        if (passwordEncoder.matches(password, loginUser.getPassword())) {
            return loginUser;
        }
        return null;
    }
}
