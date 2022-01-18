package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 기능 검증")
    void join() {
        //given
        User user = new User("test@aaa.com","test","1234");

        //when
        String savedNickname = userService.join(user);

        //then
        User findUser = userService.findOneByNickname(savedNickname).get();
        assertThat(user.getNickName()).isEqualTo(findUser.getNickName());
    }

    @Test
    @DisplayName("회원가입 시 중복 닉네임 예외 검증")
    public void duplicateUser(){
        //given
        User user1 = new User("test1@aaa.com","test","1234");
        User user2 = new User("test2@aaa.com","test","1234");

        //when
        userService.join(user1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("Nickname already exists.");
    }
}
