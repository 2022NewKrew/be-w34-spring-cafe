package com.kakao.cafe.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HttpRequestParameterTest {

    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
    }

    @Test
    @DisplayName("요청 파라미터의 한줄 String 반환 -> 파라미터 없음")
    void getStringOfParameters_emptyParameter() {
        //Given
        Map<String, String[]> emptyParamMap = Collections.emptyMap();
        given(request.getParameterMap()).willReturn(emptyParamMap);
        HttpRequestParameter httpRequestParameter = new HttpRequestParameter(request);

        //When
        String result = httpRequestParameter.getStringOfParameters();

        //Then
        assertEquals("", result);
    }

    @Test
    @DisplayName("요청 파라미터의 한줄 String 반환 -> 비밀번호 제거 확인")
    void getStringOfParameters_checkPasswordRemove() {
        //Given
        String keyForPassword = "password";
        String keyForEmail = "email";
        String valueForEmail = "gallix@kakao.com";

        Map<String, String[]> paramMap = new HashMap<>();
        paramMap.put(keyForEmail, new String[]{valueForEmail});
        paramMap.put(keyForPassword, new String[] {"abcd1234!"});

        given(request.getParameterMap()).willReturn(paramMap);
        HttpRequestParameter httpRequestParameter = new HttpRequestParameter(request);

        String expected = "[ " + keyForEmail + " : " + valueForEmail + " ]";

        //When
        String result = httpRequestParameter.getStringOfParameters();

        //Then
        assertEquals(expected, result);
    }
}