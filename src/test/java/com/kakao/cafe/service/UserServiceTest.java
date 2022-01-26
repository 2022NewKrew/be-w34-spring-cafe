package com.kakao.cafe.service;
import java.util.Optional;

import com.kakao.cafe.web.dto.UserResponse;
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
        UserResponse userResponse = userService.getUserByUserId("admin");

        //then
        assertEquals(userResponse.getUserId(),"admin");
        assertEquals(userResponse.getEmail(),"admin@kakaocorp.com");
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
        UserResponse userResponse = userService.getSessionUserDTO("test","1111").get();

        //then
        assertEquals(userResponse.getEmail(), "test@kakaocorp.com");
        assertEquals(userService.getSessionUserDTO("test","1234"),Optional.empty());
        assertEquals(userService.getSessionUserDTO("test1","1111"),Optional.empty());
    }
}