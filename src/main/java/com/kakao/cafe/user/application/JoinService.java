package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;

    public void createUser(String userId, String password, String name, String email){
        if(userRepository.getUser(userId).isPresent()){
            throw new IllegalArgumentException("id가 중복됩니다.");
        }

        User user = new User(userId, password, name, email);
        userRepository.save(user);
    }
}
