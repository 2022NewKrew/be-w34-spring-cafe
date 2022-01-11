package com.kakao.cafe.controller.rest;

import com.kakao.cafe.controller.RedirectedURL;
import com.kakao.cafe.dto.user.ProfileUpdateDto;
import com.kakao.cafe.dto.user.UserJoinDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.testutil.user.UserDtoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRestController userRestController;

    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        response = mock(HttpServletResponse.class);
    }

    @Test
    @DisplayName("회원 가입 -> 정상")
    void join() throws IOException {
        //Given
        UserJoinDto dto = UserDtoUtil.createUserJoinDto();
        String passwordBeforeEncode = dto.getPassword();

        //When
        userRestController.join(dto, response);

        //Then
        then(passwordEncoder).should(times(1)).encode(passwordBeforeEncode);
        then(userService).should(times(1)).join(dto);
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_JOIN);
    }

    @Test
    @DisplayName("프로필 수정 -> 정상")
    void updateProfile() throws IOException {
        //Given
        ProfileUpdateDto dto = UserDtoUtil.createProfileUpdateDto();
        String passwordBeforeEncode = dto.getPassword();

        //When
        userRestController.updateProfile(dto, response);

        //Then
        then(passwordEncoder).should(times(1)).encode(passwordBeforeEncode);
        then(userService).should(times(1)).updateProfile(dto);
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_UPDATE_PROFILE);
    }
}
