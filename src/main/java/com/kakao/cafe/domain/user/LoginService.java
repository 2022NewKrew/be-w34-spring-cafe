package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.error.exception.LoginException;
import com.kakao.cafe.global.error.exception.NoSuchUserException;
import com.kakao.cafe.global.error.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new LoginException(new NoSuchUserException()));
        if (!user.getPassword().equals(password))
            throw new LoginException(new PasswordNotMatchException());
        return user;
    }

}
