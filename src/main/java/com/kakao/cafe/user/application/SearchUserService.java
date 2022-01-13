package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUser(String userId){
        return userRepository.getUser(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다."));
    }
}
