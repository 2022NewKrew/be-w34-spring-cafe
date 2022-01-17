package com.kakao.cafe.repository;

import com.kakao.cafe.config.SecurityConfig;
import com.kakao.cafe.dto.UserRegistrationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class UserRepositoryTest {
    private final String userId = "testUserId";
    private final String password = "testPassword";
    private final String email = "testEmail";

    @Test
    @DisplayName("[성공] userRepository 클래스 생성")
    void userRepository() {
        new UserRepository(new SecurityConfig().passwordEncoder());
    }

    @Test
    @DisplayName("[성공] userRepository 유저 생성")
    void createUser() {
        // given
        UserRepository userRepository = new UserRepository(new SecurityConfig().passwordEncoder());
        UserRegistrationDto userDto = new UserRegistrationDto(userId, password, email);

        // when & then
        userRepository.createUser(userDto);
    }
}