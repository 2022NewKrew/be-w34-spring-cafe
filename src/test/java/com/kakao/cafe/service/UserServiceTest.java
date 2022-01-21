package com.kakao.cafe.service;
import java.util.Optional;

import com.kakao.cafe.web.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    public UserService userService;

    @Test
    void getUserByUserId() {
        //when
        UserDTO userDTO = userService.getUserByUserId("admin");

        //then
        assertEquals(userDTO.getUserId(),"admin");
        assertEquals(userDTO.getEmail(),"admin@kakaocorp.com");
    }

    @Test
    void getUserList() {
        assertEquals(userService.getUserDTOList().size(),2);
    }

    @Test
    void getUserListSize() {
        assertEquals(userService.getUserListSize(),2);
    }

    @Test
    void getSessionUserDTO(){
        //given
        UserDTO userDTO = userService.getSessionUserDTO("test","1111").get();
        //then
        assertEquals(userDTO.getEmail(), "test@kakaocorp.com");
        assertEquals(userService.getSessionUserDTO("test","1234"),Optional.empty());
        assertEquals(userService.getSessionUserDTO("test1","1111"),Optional.empty());
    }
}