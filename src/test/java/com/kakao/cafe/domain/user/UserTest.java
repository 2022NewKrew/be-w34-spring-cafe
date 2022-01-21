package com.kakao.cafe.domain.user;

import com.kakao.cafe.web.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserTest {

    @Mock
    User user = User.builder()
            .userId("cih468")
            .password("1234")
            .email("cih468@naver.com").build();

    @Test
    void getId() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void getRegisterDate() {
    }

    @Test
    void testToString() {
    }
}