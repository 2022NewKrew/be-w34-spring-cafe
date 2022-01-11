package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.model.user.UserSignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @DisplayName("정상적으로 회원가입을 진행하면 에러가 발생하지 않아야 한다.")
    @Test
    void signupUser() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = "1234";
        UserSignupRequest user = UserSignupRequest.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        assertThatNoException().isThrownBy(() -> {
            userService.signupUser(user);
        });
    }

    @DisplayName("이미 등록된 이메일을 이용하여 회원가입을 진행하면 에러가 발생한다.")
    @Test
    void signupUserWithDuplicatedEmail() {
        String email = "test@test.com";
        User user = User.builder()
                .email(email)
                .nickname("테스터1")
                .password("1234")
                .build();
        userRepository.save(user);

        UserSignupRequest userWithDuplicatedEmail = UserSignupRequest.builder()
                .email(email)
                .nickname("테스터2")
                .password("1234")
                .build();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userService.signupUser(userWithDuplicatedEmail);
        });
    }

    @DisplayName("회원 목록의 크기는 등록된 회원의 수와 동일해야 한다.")
    @Test
    void getAllUsers() {
        int size = 30;
        for (int i = 0; i < size; i++) {
            User user = User.builder()
                    .email("test" + i + "@test.com")
                    .nickname("테스터" + i)
                    .password("1234")
                    .build();
            userRepository.save(user);
        }

        List<UserDto> users = userService.getAllUsers();
        log.info("{}", users);
        assertThat(users.size()).isEqualTo(size);
    }

    @DisplayName("id를 이용하여 조회한 회원 정보는 등록된 회원 정보와 같아야 한다.")
    @Test
    void getUserById() {
        User user = User.builder()
                .email("test@test.com")
                .nickname("테스터")
                .password("1234")
                .build();
        userRepository.save(user);
        long id = user.getId();

        UserDto foundUser = userService.getUserById(id);
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.getCreatedAt()).isEqualTo(user.getCreatedAt());
    }

    @DisplayName("등록되지 않은 회원 정보를 조회하면 에러를 발생시켜야 한다.")
    @Test
    void getUserByIdNotSignup() {
        long id = 100;

        assertThatIllegalArgumentException().isThrownBy(() -> {
           UserDto user = userService.getUserById(id);
        });
    }
}
