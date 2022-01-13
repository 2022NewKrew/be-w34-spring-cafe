package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserSaveForm;
import com.kakao.cafe.controller.dto.UserJoinForm;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.AlreadyExistId;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private User user;
    private UserJoinForm joinDto;
    String userId = "idid";

    @BeforeEach
    void setting() {
        joinDto = new UserJoinForm();
        joinDto.setPassword("1234");
        joinDto.setEmail("kakao@com");
        joinDto.setUserId(userId);
        joinDto.setName("lsh");

        user = User.from(joinDto);
    }

    @Test
    @DisplayName("아이디 중복 방지 테스트")
    void join() {
        //given
        UserJoinForm dto2 = new UserJoinForm();
        dto2.setPassword("1234");
        dto2.setEmail("kakao@com");
        dto2.setUserId(userId);
        dto2.setName("lsh");

        //when
        userService.join(joinDto);

        //when
        Assertions.assertThrows(AlreadyExistId.class, () -> userService.join(dto2));
    }

    @Test
    @DisplayName("비밀번호 맞음")
    void updateUser() {
        //given
        String updateName = "newName";
        String updateEmail = "newEmail";
        UserSaveForm userSaveForm = new UserSaveForm();
        userSaveForm.setPassword(user.getPassword());
        userSaveForm.setEmail(updateEmail);
        userSaveForm.setName(updateName);

        //when
        userRepository.save(user);
        userService.updateUser(userId, userSaveForm);

        user = userRepository.findById(user.getUserId()).get();

        //then
        assertThat(user.getName()).isEqualTo(updateName);
        assertThat(user.getEmail()).isEqualTo(updateEmail);
    }

    @Test
    @DisplayName("비밀번호 틀림")
    void updateUser2() {
        //given
        String updateName = "newName";
        String updateEmail = "newEmail";
        UserSaveForm userSaveForm = new UserSaveForm();
        userSaveForm.setPassword(user.getPassword() + "a");
        userSaveForm.setEmail(updateEmail);
        userSaveForm.setName(updateName);

        //when
        userRepository.save(user);
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, userSaveForm));
    }
}
