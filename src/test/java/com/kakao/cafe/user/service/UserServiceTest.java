package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.UserDuplicatedException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.web.dto.UserSaveDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach()
    void tearDown() {
        userRepository.deleteAll();
        userRepository.save(User.builder().userId("miya.ong").password("1234").name("박예지")
            .email("miya.ong@kakaocorp.com").build());
    }

    @Test
    @DisplayName("유저 아이디 중복 테스트")
    void duplicatedUser() {
        //given
        UserSaveDto miya1 = new UserSaveDto("miya", "1234", "박예지", "miya.ong@kakaocorp.com");
        UserSaveDto miya2 = new UserSaveDto("miya", "1234", "미야옹", "miya.ong@kakaocorp.com");

        //when
        userService.addUser(miya1);

        //then
        Assertions.assertThrows(UserDuplicatedException.class, () -> {
            userService.addUser(miya2);
        });
    }

    @Test
    @DisplayName("존재하지 않는 유저 테스트")
    void noUser() {
        Assertions.assertFalse(userService.findUser("strange.user").isPresent());
    }
}
