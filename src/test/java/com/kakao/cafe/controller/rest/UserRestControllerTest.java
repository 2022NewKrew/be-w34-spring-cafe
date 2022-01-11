package com.kakao.cafe.controller.rest;

import com.kakao.cafe.controller.RedirectedURL;
import com.kakao.cafe.dto.UserJoinDto;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserRestController userRestController;

    @Test
    @DisplayName("회원 가입 -> 정상")
    void join() throws IOException {
        //Given
        UserJoinDto dto = createUserJoinDtoInstance();
        HttpServletResponse response = mock(HttpServletResponse.class);
        String passwordBeforeEncode = dto.getPassword();

        //When
        userRestController.join(dto, response);

        //Then
        then(passwordEncoder).should(times(1)).encode(passwordBeforeEncode);
        then(userService).should(times(1)).join(dto);
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_JOIN);
    }

    private UserJoinDto createUserJoinDtoInstance() {
        return UserJoinDto.builder()
                .email("gallix@kakao.com")
                .password("abcd1234!")
                .nickName("gallix")
                .build();
    }
}
