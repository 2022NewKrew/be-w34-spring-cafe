package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.model.user.UserSignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

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
        log.info("{}", user.getEmail().equals(userWithDuplicatedEmail.getEmail()));
        log.info("{}", userRepository.findAll());
        assertThatIllegalArgumentException().isThrownBy(() -> {
            userService.signupUser(userWithDuplicatedEmail);
        });
    }
}
