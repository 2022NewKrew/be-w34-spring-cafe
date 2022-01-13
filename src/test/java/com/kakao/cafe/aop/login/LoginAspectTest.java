package com.kakao.cafe.aop.login;

import com.kakao.cafe.error.exception.nonexist.SessionAttributesNotFoundedException;
import com.kakao.cafe.util.SessionUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginAspectTest {

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoginAspect loginAspect;

    @Test
    @DisplayName("세션에 로그인 유저의 ID가 없다면 예외 발생 -> 정상")
    void checkLoginIdSessionIsNotNull() {
        //Given
        given(session.getAttribute(SessionUtil.LOGIN_USER_ID)).willReturn(null);

        //When, Then
        assertThrows(SessionAttributesNotFoundedException.class, () -> loginAspect.checkLoginIdSessionIsNotNull());
    }
}