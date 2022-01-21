package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void join() {
        //given
        User user = new User();
        user.setUserId("test");
        user.setName("test");
        user.setPassword("test");
        user.setEmail("test");

        //when
        userService.join(user);

        //then
        User findUser = userService.findUser(user.getUserId());
        assertThat(user.getUserId()).isEqualTo(findUser.getUserId());
    }

    @Test
    void validateDuplicateUser() {
        //given
        User user1 = new User();
        user1.setUserId("user1");
        user1.setName("user1");
        user1.setPassword("user1");
        user1.setEmail("user1");

        User user2 = new User();
        user2.setUserId("user1");
        user2.setName("user1");
        user2.setPassword("user1");
        user2.setEmail("user1");

        //when
        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
