package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.dto.AuthInfoDTO.Login;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.AuthInvalidUidException;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AuthServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("잘못된 UID 입력으로 조회 불가능한 상황 테스트")
    void login() {
        // Given
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.empty());

        // When
        Login loginDTO = new Login("uid", "pwd");
        AuthInvalidUidException exception = assertThrows(AuthInvalidUidException.class,
            () -> authService.login(loginDTO));

        // Then
        assertThat(exception.getMessage())
            .contains("Not Found");
    }

    @Test
    @DisplayName("잘못된 Password 입력으로 인증 불가능한 상황 테스트")
    void login2() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When
        Login loginDTO = new Login("uid", "wrongPwd");
        AuthInvalidPasswordException exception = assertThrows(AuthInvalidPasswordException.class,
            () -> authService.login(loginDTO));

        // Then
        assertThat(exception.getMessage())
            .contains("Invalid Password");
    }

    @Test
    @DisplayName("정상적인 로그인 정보 입력 테스트")
    void login3() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When
        Login loginDTO = new Login("uid", "pwd");

        // Then
        assertDoesNotThrow(() -> authService.login(loginDTO));
    }
}