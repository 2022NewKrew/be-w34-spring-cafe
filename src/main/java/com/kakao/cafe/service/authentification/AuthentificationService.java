package com.kakao.cafe.service.authentification;

import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final UserRepository userRepository;

    public UserIdentification login(String userId, String password) {
        User loginUser = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        if(!loginUser.getPassword().equals(password)) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
        return UserIdentification.of(loginUser.getUserId(), loginUser.getUserName(), loginUser.getEmail());
    }
}
