package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoService {
    private final UserRepository userRepository;

    public void updateUserInfo(String id, UserInfo userInfo) {
        userRepository.update(id, userInfo);
    }
}
