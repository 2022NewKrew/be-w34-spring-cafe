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
    User user = new User(new UserDTO("cih468","1234","cih468@naver.com"));

    @Test
    void newInstance() {
        User.newInstance(2,"cih468","1234","cih468@naver.com","2022-01-19");
    }

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