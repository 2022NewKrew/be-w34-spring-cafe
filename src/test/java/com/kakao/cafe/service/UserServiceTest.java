package com.kakao.cafe.service;
import java.util.Optional;

import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserResponseDTO;
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
        UserResponseDTO userResponseDTO = userService.getUserByUserId("admin");

        //then
        assertEquals(userResponseDTO.getUserId(),"admin");
        assertEquals(userResponseDTO.getEmail(),"admin@kakaocorp.com");
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
        UserResponseDTO userResponseDTO = userService.getSessionUserDTO("test","1111").get();

        //then
        assertEquals(userResponseDTO.getEmail(), "test@kakaocorp.com");
        assertEquals(userService.getSessionUserDTO("test","1234"),Optional.empty());
        assertEquals(userService.getSessionUserDTO("test1","1111"),Optional.empty());
    }
}