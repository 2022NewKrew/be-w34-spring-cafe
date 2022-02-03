package com.kakao.cafe.system;

import com.kakao.cafe.exception.NoSuchUserIdException;
import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
    private final Logger log = LoggerFactory.getLogger(LoginService.class);

    public User login(String userId, String password) {
        try {
            User loginUser = userRepository.findUserByUserId(userId);

            if (loginUser == null) {
                return null;
            }

            if (passwordEncoder.matches(password, loginUser.getPassword())) {
                return loginUser;
            }

        } catch (DataAccessException e) {
            log.error("일치하는 userId가 존재하지 않습니다.");
            throw new NoSuchUserIdException();
        }
        return null;
    }
}
