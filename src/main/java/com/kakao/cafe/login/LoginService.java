package com.kakao.cafe.login;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NoSuchUser;
import com.kakao.cafe.exception.PasswordMismatch;
import com.kakao.cafe.login.dto.UserLogin;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(UserLogin userLogin) throws Exception {
        Optional<User> findUser = userRepository.findById(userLogin.getUserId());
        User user = findUser.orElseThrow(() -> {
            throw new NoSuchUser();
        });
        if(!user.getPassword().equals(userLogin.getPassword())) {
            throw new PasswordMismatch();
        }
        return user;
    }
}
