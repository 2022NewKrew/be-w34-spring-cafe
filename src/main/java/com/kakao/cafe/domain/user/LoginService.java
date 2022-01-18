package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.error.exception.LoginException;
import com.kakao.cafe.global.error.exception.NoSuchUserException;
import com.kakao.cafe.global.error.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 로그인 기능 서비스
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String userId, String password) {
        // 해당 아이디의 유저가 존재하지 않을 경우 LoginException의 cause를 NoSuchUserException으로 설정
        // LoginException은 ExceptionHandler 에서 login_failed.html view를 반환하도록 함
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new LoginException(new NoSuchUserException()));
        if (!user.getPassword().equals(password))
            // 비밀번호 불일치할 경우 LoginException의 cause를 NoSuchUserException으로 설정
            throw new LoginException(new PasswordNotMatchException());
        return user;
    }

}
