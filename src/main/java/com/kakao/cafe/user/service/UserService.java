package com.kakao.cafe.user.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(String stringId, String email, String name, String password) {
        User user = makeUser(stringId, password, name, email);
        return userRepository.persist(user);
    }

    // 페이징 구현 시 리펙토링 필요
    public AllUserProfileServiceResponse getAllUserViewData(Long startIndex) {
        List<User> users = userRepository.findAll();
        List<User> result = users.subList(startIndex.intValue(), users.size());
        Collections.reverse(result);
        return new AllUserProfileServiceResponse(result);
    }

    public UserProfileServiceResponse getUserProfile(String stringId) {
        Optional<User> op = userRepository.find(stringId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserServiceDTOMapper.convertToUserProfileServiceResponse(user);
    }

    public void updateUserInfo(String stringId, String oldPassword, String newPassword, String name, String email) {
        // 검증
        validateUser(stringId, oldPassword);
        User user = makeUser(stringId, newPassword, name, email);
        userRepository.updateUserInfo(user);
    }

    public Long validateUser(String stringId, String password) {
        Optional<User> op = userRepository.find(stringId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        return user.getId();
    }


    private User makeUser(String stringId, String password, String name, String email) {
        return User.builder()
                   .stringId(stringId)
                   .password(password)
                   .name(name)
                   .email(email)
                   .build();
    }

}
