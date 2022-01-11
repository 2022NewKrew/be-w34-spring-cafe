package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserFormDto;
import com.kakao.cafe.controller.dto.UserJoinDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.AlreadyExistId;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String join(UserJoinDto dtoUser) {
        if(isAlreadyExist(dtoUser)) {
            throw new AlreadyExistId("이미 존재하는 아이디");
        }

        User user = User.from(dtoUser);
        userRepository.save(user);
        return user.getUserId();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUser(String userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(String userId, UserFormDto dto) {
        User user = userRepository.findById(userId);
        if(!user.chcekPassword(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        user.updateEmailAndName(dto.getEmail(), dto.getName());
        userRepository.update(user);
    }

    private boolean isAlreadyExist(UserJoinDto dtoUser) {
        return userRepository.findAll().stream().anyMatch(user -> user.getUserId().equals(dtoUser.getUserId()));
    }
}
