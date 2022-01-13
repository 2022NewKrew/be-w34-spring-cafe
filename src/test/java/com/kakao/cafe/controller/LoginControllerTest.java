package com.kakao.cafe.controller;

import com.kakao.cafe.util.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoginController loginController;

    private ModelAndView mav;

    @BeforeEach
    void setUp() {
        mav = mock(ModelAndView.class);
    }

    @Test
    @DisplayName("로그인 화면 반환 -> 로그인 페이지가 아니고 다른 페이지에서 접속했을 때, 세션 확인")
    void loginView_fromAnotherView_checkSession() {
        //Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        String referer = "/users";
        given(request.getHeader("Referer")).willReturn(referer);

        //When
        loginController.loginView(request, mav);

        //Then
        then(session).should(times(1)).setAttribute(SessionUtil.AFTER_LOGIN_REDIRECTED_URL, referer);
    }

    @Test
    @DisplayName("로그인 화면 반환 -> 로그인 페이지에서 접근(새로고침 같은 방식으로), 세션 확인")
    void loginView_fromLoginView_checkSession() {
        //Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        given(request.getHeader("Referer")).willReturn("/login");

        //When
        loginController.loginView(request, mav);

        //Then
        then(session).should(never()).setAttribute(anyString(), anyString());
    }

    @Test
    @DisplayName("로그인 화면 반환 -> 정상, mav 확인")
    void loginView_checkMav() {
        //Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        String referer = "/users";
        given(request.getHeader("Referer")).willReturn(referer);

        //When
        loginController.loginView(request, mav);

        //Then
        then(mav).should(times(1)).setViewName("login");
    }
}