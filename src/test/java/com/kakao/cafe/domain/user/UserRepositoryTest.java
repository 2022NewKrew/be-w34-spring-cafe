package com.kakao.cafe.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final List<User> users = new ArrayList<>();

    @BeforeEach
    private void setup() {
        setUsersForTest();
    }

    private void setUsersForTest() {
        int sizeOfUsers = 10;
        for (int i = 0; i < sizeOfUsers; i++) {
            User user = User.builder()
                    .email(createEmailForTest(i))
                    .nickname("테스터" + i)
                    .password("" + i)
                    .build();
            users.add(user);
            userRepository.save(user);
        }
    }

    private String createEmailForTest(int i) {
        return "test" + i + "@test.com";
    }

    @AfterEach
    void cleanup() {
        users.clear();
        userRepository.deleteAll();
    }

    @DisplayName("중복되지 않은 이메일 주소를 이용하여 회원가입을 한 사용자의 id는 null 이 아니다.")
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

        userRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @DisplayName("사용자가 null 이라면 예외를 발생시켜야 한다.")
    @Test
    void saveNull() {
        User user = null;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userRepository.save(user);
        });
    }

    @DisplayName("이미 등록된 이메일로 회원가입을 하면 에러가 발생한다.")
    @Test
    void saveDuplicatedEmail() {
        String email = users.get(0).getEmail();
        User user = User.builder()
                .email(email)
                .nickname("테스터1")
                .password("1234")
                .build();

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

        assertThat(users.size()).isEqualTo(this.users.size() + 1);
    }

    @DisplayName("회원 목록을 전체 삭제하면 저장되어 있는 회원 목록의 크기는 0 이다.")
    @Test
    void deleteAll() {
        userRepository.deleteAll();
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(0);
    }

    @DisplayName("id를 이용하여 회원을 조회하면 해당 회원의 정보가 조회되어야 한다.")
    @Test
    void findById() {
        int index = 5;

        User user = userRepository.findById(index + 1).orElse(null);
        User expected = users.get(index);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(expected.getId());
        assertThat(user.getEmail()).isEqualTo(expected.getEmail());
        assertThat(user.getPassword()).isEqualTo(expected.getPassword());
        assertThat(user.getCreatedAt()).isEqualTo(user.getCreatedAt());
    }

    @DisplayName("이메일을 이용하여 회원을 조회하면 해당 회원의 정보가 조회되어야 한다.")
    @Test
    void findByEmail() {
        int index = 7;
        String email = createEmailForTest(index);

        User user = userRepository.findByEmail(email).orElse(null);
        User expected = users.get(index);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(expected.getId());
        assertThat(user.getEmail()).isEqualTo(expected.getEmail());
        assertThat(user.getPassword()).isEqualTo(expected.getPassword());
        assertThat(user.getCreatedAt()).isEqualTo(user.getCreatedAt());
    }

    @DisplayName("null인 사용자의 정보를 업데이트 요청하면 에러를 발생시켜야 한다.")
    @Test
    void updateNull() {
        User user = null;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userRepository.update(user);
        });
    }

    @DisplayName("등록되지 않은 사용자의 정보를 업데이트 요청하면 에러를 발생시켜야 한다.")
    @Test
    void updateNotSignupUser() {
        long id = 12345678910L;
        String email = "abc1234@abc.com";
        String nickname = "ABC";
        String password = "abcdef";
        LocalDateTime createdAt = LocalDateTime.now();
        User userForUpdate = User.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .password(password)
                .createdAt(createdAt)
                .build();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userRepository.update(userForUpdate);
        });
    }

    @DisplayName("등록된 사용자의 정보를 업데이트 요청하면 요청한 정보와 동일해야 한다.")
    @Test
    void update() {
        String email = "abc1234@abc.com";
        String nickname = "ABC";
        String password = "abcdef";
        LocalDateTime createdAt = LocalDateTime.now();
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .createdAt(createdAt)
                .build();
        userRepository.save(user);
        String updatedEmail = "test@test.com";
        String updatedNickname = "TEST";
        String updatedPassword = "test";
        User userForUpdate = User.builder()
                .id(user.getId())
                .email(updatedEmail)
                .nickname(updatedNickname)
                .password(updatedPassword)
                .createdAt(createdAt)
                .build();

        userRepository.update(userForUpdate);

        User updatedUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(user.getId());
        assertThat(updatedUser.getEmail()).isEqualTo(updatedEmail);
        assertThat(updatedUser.getNickname()).isEqualTo(updatedNickname);
        assertThat(updatedUser.getPassword()).isEqualTo(updatedPassword);
    }

}
