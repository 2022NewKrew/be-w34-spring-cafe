package com.kakao.cafe.domain.login;

import com.kakao.cafe.core.exception.LoginFailed;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.core.exception.NoSuchUser;
import com.kakao.cafe.core.exception.PasswordMismatch;
import com.kakao.cafe.domain.login.dto.UserLogin;
import com.kakao.cafe.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
//    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public User login(UserLogin userLogin) throws Exception {
        Optional<User> login = loginRepository.login(userLogin);
        return login.orElseThrow(() -> {
            throw new LoginFailed();
        });
    }

//    public User login(UserLogin userLogin) throws Exception {
//        Optional<User> findUser = userRepository.findByUserId(userLogin.getUserId());
//        User user = findUser.orElseThrow(() -> {
//            throw new NoSuchUser();
//        });
//        if(!user.getPassword().equals(userLogin.getPassword())) {
//            throw new PasswordMismatch();
//        }
//        return user;
//    }
}
