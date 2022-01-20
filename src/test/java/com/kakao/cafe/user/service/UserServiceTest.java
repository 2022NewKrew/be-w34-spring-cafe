package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.exception.UsernameDuplicatedException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest{
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("중복 가입 시 에러 발생해야 함")
    public void givenDuplicateUserCreationForm_WhenAddingUser_ThenThrowUsernameDuplicatedException() {
        // Given
        UserCreationForm userCreationForm = new UserCreationForm("DuplicateUsername", "ValidPassword", "valid@email.com", "VALID!");
        given(userRepository.get("DuplicateUsername")).willReturn(Optional.of(User.builder().build()));

        // When Then
        assertThrows(UsernameDuplicatedException.class, () -> userService.addUser(userCreationForm));
    }

    @Test
    @DisplayName("존재하지 않는 유저 호출 시 에러 발생해야 함")
    public void givenNonexistentUsername_WhenCreatingUserView_ThenThrowUserNotFoundException() {
        // Given
        String username = "Nobody";

        // When Then
        assertThrows(UserNotFoundException.class, () -> userService.getUserViewByUsername(username));
    }

}
