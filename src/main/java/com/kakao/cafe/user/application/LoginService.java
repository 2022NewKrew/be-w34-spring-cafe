package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(String userId, String password){
        User user = userRepository.getUser(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
        );

        if(!user.match(password)){
            throw new IllegalArgumentException("패스워드가 맞지 않습니다.");
        }

        return user;
    }
}
