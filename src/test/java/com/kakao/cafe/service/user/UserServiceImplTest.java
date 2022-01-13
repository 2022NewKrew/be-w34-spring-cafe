package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserReqDto;
import com.kakao.cafe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserServiceImplTest {

    private final UserRepository userRepository;
    UserReqDto userReqDto = new UserReqDto("test", "1234", "testName", "test@kakaocorp.com");

    @Test
    void addUser() {

    }

    @Test
    void findUsers() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void updateUser() {
    }
}