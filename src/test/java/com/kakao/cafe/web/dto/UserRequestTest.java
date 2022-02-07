package com.kakao.cafe.web.dto;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {

    @Mock
    UserRequest userRequest;

    @Test
    void newInstance() {
        this.userRequest = UserRequest.newInstance("cih468","1234","cih468@naver.com");
    }

    @Test
    void getPassword() {
        newInstance();
        assertEquals(userRequest.getPassword(), "1234");
    }

    @Test
    void getUserId() {
        newInstance();
        assertEquals(userRequest.getUserId(), "cih468");
    }

}