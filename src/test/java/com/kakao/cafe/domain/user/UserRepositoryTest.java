package com.kakao.cafe.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private static final int SIZE_OF_USERS = 10;

    @BeforeEach
    private void setup() throws Exception {
        setUsersForTest();
    }

    private void setUsersForTest() throws Exception {
        for (int i = 0; i < UserRepositoryTest.SIZE_OF_USERS; i++) {
            User user = User.builder()
                    .email("test" + i + "@test.com")
                    .nickname("테스터" + i)
                    .password("" + i)
                    .build();
            userRepository.save(user);
        }
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @DisplayName("정상적으로 회원가입을 한 사용자의 id는 저장되어 있는 회원 목록의 크기 + 1 과 같다.")
    @Test
    void save() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = "1234";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        assertThatNoException().isThrownBy(() -> {
            userRepository.save(user);
            assertThat(user.getId()).isEqualTo(SIZE_OF_USERS + 1);
        });
    }

    @DisplayName("사용자가 null 이라면 예외를 발생시켜야 한다.")
    @Test
    void saveForNull() {
        User user = null;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userRepository.save(user);
        });
    }

    @DisplayName("회원가입을 하면 저장되어 있는 회원 목록의 크기는 1 증가해야 한다.")
    @Test
    void findAll() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = "1234";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
        assertThatNoException().isThrownBy(() -> {
            userRepository.save(user);
        });

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(SIZE_OF_USERS + 1);
    }

    @DisplayName("회원 목록을 전체 삭제하면 저장되어 있는 회원 목록의 크기는 0 이다.")
    @Test
    void deleteAll() {
        userRepository.deleteAll();
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(0);
    }

}
