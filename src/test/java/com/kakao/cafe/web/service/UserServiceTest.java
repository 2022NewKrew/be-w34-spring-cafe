package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.repository.user.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void join() {
        //given
        User user = new User();
        user.setUserId("test");

        //when
        userService.join(user);

        //then
        User findUser = userService.findUser(user.getUserId());
        assertThat(user).isEqualTo(findUser);
    }

    @Test
    void validateDuplicateUser() {
        //given
        User user1 = new User();
        user1.setUserId("user1");

        User user2 = new User();
        user2.setUserId("user1");

        //when
        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
