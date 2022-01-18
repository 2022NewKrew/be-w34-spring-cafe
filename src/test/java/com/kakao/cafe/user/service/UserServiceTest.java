package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.UserDuplicatedException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.web.dto.UserLoginDto;
import com.kakao.cafe.user.web.dto.UserSaveDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    /*@BeforeEach
    void setUp() {
    }

    @AfterEach()
    void tearDown() {
        userRepository.deleteAll();
        userRepository.save(User.builder().userId("miya.ong").password("1234").name("박예지")
            .email("miya.ong@kakaocorp.com").build());
    }*/

    @Test
    @DisplayName("유저 아이디 중복 테스트")
    void duplicatedUser() {
        //given
        UserSaveDto miya1 = new UserSaveDto("miya", "1234", "박예지", "miya.ong@kakaocorp.com");
        UserSaveDto miya2 = new UserSaveDto("miya", "1234", "미야옹", "miya.ong@kakaocorp.com");

        //when
        userService.addUser(miya1);

        //then
        Assertions.assertThrows(UserDuplicatedException.class, () -> userService.addUser(miya2));
    }

    @Test
    @DisplayName("존재하지 않는 유저 테스트")
    void noUser() {
        Assertions.assertFalse(userService.findUser("strange.user").isPresent());
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccess() {
        UserLoginDto user = new UserLoginDto("miya.ong", "1234");

        Assertions.assertEquals(userService.login(user).getUserId(), user.getUserId());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void loginFailure() {
        UserLoginDto unknownUser = new UserLoginDto("unknown", "1234");
        UserLoginDto wrongPassword = new UserLoginDto("miya.ong", "abcd");

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.login(unknownUser));
        Assertions.assertThrows(UserNotFoundException.class,
            () -> userService.login(wrongPassword));
    }
}
