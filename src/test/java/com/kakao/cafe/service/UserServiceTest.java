package com.kakao.cafe.service;

import java.util.ArrayList;
import java.util.List;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    public UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        this.userService = new UserService(userRepository);
    }

    @Test
    void createUser() {
        userService.createUser(new UserDTO("cih468", "1234", "cih468@naver.com"));
    }

    @Test
    void getUserByUserId() {
        when(userRepository.findByUserId("cih468")).thenReturn(User.newInstance(2, "cih468", "1234", "cih468@naver.com", "2022-01-19"));
        UserDTO userDTO = userService.getUserByUserId("cih468");
        assertEquals(userDTO.getId(), 2);
        assertEquals(userDTO.getUserId(), "cih468");
        verify(userRepository, times(1)).findByUserId("cih468");
    }

    @Test
    void getUserList() {

    }

    @Test
    void getUserListSize() {
        List<User> userList = new ArrayList<>();
        userList.add(User.newInstance(1, "admin", "1111", "admin@naver.com", "2022-01-09"));
        userList.add(User.newInstance(2, "cih468", "1234", "cih468@naver.com", "2022-01-19"));
        when(userRepository.getUserList()).thenReturn(userList);
        assertEquals(userService.getUserListSize(), 2);
    }
}