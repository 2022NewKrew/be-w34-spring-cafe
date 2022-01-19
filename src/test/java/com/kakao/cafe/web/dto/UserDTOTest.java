package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    User user = User.newInstance(2, "cih468", "1234", "cih468@naver.com", "2022-01-19");

    @Mock
    UserDTO userDTO;

    @Test
    void newInstance() {
        this.userDTO = UserDTO.newInstance(user);
    }

    @Test
    void getId() {
        newInstance();
        assertEquals(2, this.userDTO.getId());
    }

    @Test
    void getPassword() {
        newInstance();
        assertEquals(userDTO.getPassword(), "1234");
    }

    @Test
    void getUserId() {
        newInstance();
        assertEquals(userDTO.getUserId(), "cih468");
    }

}