package com.kakao.cafe.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.dto.LoginRequest;
import com.kakao.cafe.user.dto.SessionUser;
import com.kakao.cafe.user.exception.DuplicatedEmailException;
import com.kakao.cafe.user.exception.DuplicatedNicknameException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private final User user = User.builder()
        .id(1L)
        .email("test@test.com")
        .password("password")
        .nickname("tester")
        .createdAt(LocalDateTime.now())
        .build();

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @DisplayName("회원가입 - 이메일이 이미 존재하면 예외 발생")
    @Test
    void signup_DuplicatedEmail_Exception() {
        given(userRepository.existsByEmail(anyString())).willReturn(Boolean.TRUE);

        assertThatThrownBy(() -> userService.signup(user))
            .isInstanceOf(DuplicatedEmailException.class)
            .hasMessage("Bad Request Error: 해당 이메일이 이미 존재합니다.");
    }

    @DisplayName("회원가입 - 닉네임이 이미 존재하면 예외 발생")
    @Test
    void signup_DuplicatedNickname_Exception() {
        given(userRepository.existsByEmail(anyString())).willReturn(Boolean.FALSE);
        given(userRepository.existsByNickname(anyString())).willReturn(Boolean.TRUE);

        assertThatThrownBy(() -> userService.signup(user))
            .isInstanceOf(DuplicatedNicknameException.class)
            .hasMessage("Bad Request Error: 해당 닉네임이 이미 존재합니다.");
    }

    @DisplayName("id로 회원 조회 - 해당 회원이 존재하지 않으면 예외 발생")
    @Test
    void getProfileById_IdNotExists_Exception() {
        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getProfileById(1L))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("No Resource Error: 해당 유저가 존재하지 않습니다.");
    }

    @DisplayName("로그인 - 해당 이메일이 존재하지 않으면 예외 발생")
    @Test
    void login_EmailNotExists_Exception() {
        LoginRequest request = new LoginRequest(user.getEmail(), user.getPassword());
        given(userRepository.findByEmail(anyString())).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.login(request))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("No Resource Error: 해당 유저가 존재하지 않습니다.");
    }

    @DisplayName("로그인 - 비밀번호가 일치하지 않으면 예외 발생")
    @Test
    void login_PasswordNotCorrespond_Exception() {
        LoginRequest request = new LoginRequest(user.getEmail(), "wrongPassword");
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.login(request))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("No Resource Error: 해당 유저가 존재하지 않습니다.");
    }

    @DisplayName("로그인 - 이메일과 비밀번호가 모두 일치하면 로그인 성공")
    @Test
    void login_Correspond_Success() {
        LoginRequest request = new LoginRequest(user.getEmail(), user.getPassword());
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

        SessionUser sessionUser = userService.login(request);

        assertThat(sessionUser.getId()).isEqualTo(user.getId());
        assertThat(sessionUser.getNickname()).isEqualTo(user.getNickname());
    }
}