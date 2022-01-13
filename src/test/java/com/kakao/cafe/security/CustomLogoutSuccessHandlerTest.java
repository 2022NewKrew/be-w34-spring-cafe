package com.kakao.cafe.security;

import com.kakao.cafe.constant.RedirectedURL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CustomLogoutSuccessHandlerTest {

    @InjectMocks
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Test
    @DisplayName("로그 아웃 처리 -> 정상")
    void onLogoutSuccess() throws IOException {
        //Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getSession()).willReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);

        //When
        customLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        //Then
        then(session).should(times(1)).invalidate();
        then(response).should(times(1)).sendRedirect(RedirectedURL.AFTER_LOGOUT);
    }
}