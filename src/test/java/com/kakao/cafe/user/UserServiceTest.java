package com.kakao.cafe.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final UserDto testUserDto;

    static {
        String userId = "melodist";
        String password = "test";
        String name = "test";
        String email = "test@email.com";

        testUserDto = new UserDto();

        testUserDto.setUserId(userId);
        testUserDto.setPassword(password);
        testUserDto.setName(name);
        testUserDto.setEmail(email);
    }

    @Test
    public void addUserTest(){
        // when
        userService.addUser(testUserDto);
        User user = userService.findAll().get(0);

        // then
        assertThat(user.getUserId()).isEqualTo(testUserDto.getUserId());
        assertThat(passwordEncoder.matches(testUserDto.getPassword(), user.getPassword()));
    }

    @Test
    public void updateUserTest(){
        // given
        String password = "test";
        String name = "test1";
        String email = "test1@email.com";

        UserDto userDto = new UserDto();

        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setEmail(email);

        userService.addUser(testUserDto);
        Integer id = userService.findAll().get(0).getId();

        // when
        User updatedUser = userService.updateUser(userDto, id);

        // then
        assertThat(updatedUser)
                .usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(userDto.toEntity());
    }

    @Test
    public void updateUserFailTest(){
        // given
        String password = "test1";
        String name = "test1";
        String email = "test1@email.com";

        UserDto userDto = new UserDto();

        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setEmail(email);

        userService.addUser(testUserDto);
        Integer id = userService.findAll().get(0).getId();

        // when
        User updatedUser = userService.updateUser(userDto, id);

        // then
        assertThat(updatedUser).isNull();
    }
}
