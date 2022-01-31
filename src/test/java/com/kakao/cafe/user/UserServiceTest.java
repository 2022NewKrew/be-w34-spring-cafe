package com.kakao.cafe.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-01-31 031
 * Time: 오후 11:16
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUserTest(){
        // given
        String userId = "melodist";
        String password = "test";
        String name = "test";
        String email = "test@email.com";

        UserDto userDto = new UserDto();

        userDto.setUserId(userId);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setEmail(email);

        // when
        userService.addUser(userDto);
        User user = userService.findAll().get(0);

        // then
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getPassword()).isNotEqualTo(password);
    }

}
