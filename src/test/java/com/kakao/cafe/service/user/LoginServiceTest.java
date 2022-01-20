package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.model.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@Transactional
@Slf4j
@SpringBootTest
class LoginServiceTest {

    @Qualifier("userJdbcRepositoryImpl")
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;

    private User user;

    @BeforeEach
    private void setup() {
        User user = User.builder()
                .email("test@test.com")
                .nickname("테스터")
                .password("1234")
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        this.user = userRepository.findByEmail(user.getEmail()).orElse(null);
    }

    @DisplayName("로그인 정상 테스트 - 로그인한 사용자의 id는 일치해야 한다.")
    @Test
    void login() {
        String email = user.getEmail();
        String password = user.getPassword();

        UserDto loginUser = loginService.login(email, password);

        assertThat(loginUser).isNotNull();
        assertThat(loginUser.getId()).isEqualTo(user.getId());
    }

    @DisplayName("로그인 실패 테스트 - 등록된 이메일이 아니라면 에러를 발생시켜야 한다.")
    @Test
    void login_emailNotExist() {
        String email = "abc@abc.com";
        String password = "1234";

        assertThatIllegalArgumentException().isThrownBy(() -> {
            UserDto loginUser = loginService.login(email, password);
        });
    }


    @DisplayName("로그인 실패 테스트 - 비밀번호가 일치하지 않는다면 에러를 발생시켜야 한다.")
    @Test
    void login_passwordNotMatch() {
        String email = user.getEmail();
        String password = user.getPassword() + "1234";

        assertThatIllegalArgumentException().isThrownBy(() -> {
            UserDto loginUser = loginService.login(email, password);
        });
    }

}
