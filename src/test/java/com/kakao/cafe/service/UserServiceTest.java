package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Update;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.UserAlreadyExistsException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.AuthInfo;
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
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("이미 존재하는 UID 추가 시 오류 테스트")
    void create() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When
        Create createDTO = new Create("uid", "pwd", "name", "email@test.com");
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
            () -> userService.create(createDTO));

        // Then
        assertThat(exception.getMessage())
            .contains("Already Exists User");
        then(userRepository)
            .should(never())
            .save(any());
    }

    @Test
    @DisplayName("새로운 User 추가 시 정상 동작 테스트")
    void create2() {
        // Given
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.empty());

        // When
        Create createDTO = new Create("uid", "pwd", "name", "email@test.com");
        assertDoesNotThrow(() -> userService.create(createDTO));

        // Then
        then(userRepository)
            .should()
            .save(any());
    }

    @Test
    @DisplayName("올바른 인증 정보를 통한 사용자 정보 수정 테스트")
    void update() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        Update updateDTO = new Update("pwd", "name", "email@test.com");
        assertDoesNotThrow(() -> userService.update(authInfo, updateDTO));

        // Then
        then(userRepository)
            .should()
            .update(any(), any(), any());
    }

    @Test
    @DisplayName("잘못된 비밀번호를 통한 사용자 정보 수정 테스트")
    void update2() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        Update updateDTO = new Update("invalidPwd", "name", "email@test.com");
        AuthInvalidPasswordException exception = assertThrows(AuthInvalidPasswordException.class,
            () -> userService.update(authInfo, updateDTO));

        // Then
        assertThat(exception.getMessage())
            .contains("Invalid Password");
        then(userRepository)
            .should(never())
            .update(any(), any(), any());
    }

    @Test
    @DisplayName("Repository 에서 User 정보를 읽어오지 못한 경우 테스트")
    void readByUid() {
        // Given
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.empty());

        // When
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
            () -> userService.readByUid("uid"));

        // Then
        assertThat(exception.getMessage())
            .contains("Not Found User");
    }

    @Test
    @DisplayName("Repository 에서 User 정보를 읽어온 경우 테스트")
    void readByUid2() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When

        // Then
        assertDoesNotThrow(() -> userService.readByUid("uid"));
    }
}