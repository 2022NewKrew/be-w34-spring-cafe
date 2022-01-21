package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.InvalidUsernamePasswordException;
import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserLoginForm;
import com.kakao.cafe.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    HttpSession session;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    LoginService loginService;

    @Test
    @DisplayName("정상 로그인")
    public void givenValidUsernamePassword_WhenLoggingIn_ThenSuccess() {
        // Given
        Long id = 1L;
        String username = "username";
        String password = "password";
        UserLoginForm userLoginForm = new UserLoginForm(username, password);
        given(userRepository.getByUsername(username)).willReturn(Optional.of(User.builder().id(1L).username(username).password(password).build()));

        // When
        LoggedInUser loggedInUser = loginService.login(userLoginForm);

        // Then
        assertEquals(id, loggedInUser.getId());
        assertEquals(username, loggedInUser.getUsername());
    }

    @Test
    @DisplayName("존재하지 않는 사용자로 로그인 시 에러 발생")
    public void givenNonexistentUsername_WhenLoggingIn_ThenThrowInvalidUsernamePasswordException() {
        // Given
        String username = "username";
        String password = "password";
        UserLoginForm userLoginForm = new UserLoginForm(username, password);
        given(userRepository.getByUsername(username)).willReturn(Optional.empty());

        // When Then
        assertThrows(InvalidUsernamePasswordException.class, () -> loginService.login(userLoginForm));
    }

    @Test
    @DisplayName("잘못된 비밀번호로 로그인 시 에러 발생해야 함")
    public void givenInvalidPassword_WhenLoggingIn_ThenThrowInvalidUsernamePasswordException() {
        // Given
        String username = "username";
        String input_password = "password";
        String actual_password = "password+a";
        UserLoginForm userLoginForm = new UserLoginForm(username, input_password);
        given(userRepository.getByUsername(username)).willReturn(Optional.of(User.builder().id(1L).username(username).password(actual_password).build()));

        // When Then
        assertThrows(InvalidUsernamePasswordException.class, () -> loginService.login(userLoginForm));
    }
}
