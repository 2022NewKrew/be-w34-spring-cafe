package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;

    public User save(User user){
        if(userRepository.getUser(user.getUserId()).isPresent()){
            throw new IllegalArgumentException("id가 중복됩니다.");
        }

        userRepository.save(user);
        return user;
    }
}
