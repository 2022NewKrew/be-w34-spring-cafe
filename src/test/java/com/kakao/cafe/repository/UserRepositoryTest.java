package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void getUserList() {
        //when
        List<User> userList = userRepository.getUserList();

        //then
        assertEquals(userList.size(), 2);
    }

    @Test
    void findByUserId() {
        //given
        Optional<User> userOptional = userRepository.findByUserId("test");

        //when
        User user = userOptional.get();

        //then
        assertEquals(user.getUserId(), "test");
        assertEquals(user.getId(), 2);
        assertEquals(user.getPassword(), "1111");
    }
}