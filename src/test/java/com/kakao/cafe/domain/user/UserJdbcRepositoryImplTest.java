package com.kakao.cafe.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@Slf4j
@SpringBootTest
class UserJdbcRepositoryImplTest {
    private static final int INIT_SIZE_OF_USERS = 3; // 데이터베이스에 저장된 초기 회원의 수

    @Qualifier("userJdbcRepositoryImpl")
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    private void setup() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = "1234";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        this.user = userRepository.findByEmail(email).orElse(null);
    }

    @DisplayName("회원 정보를 등록할 때, 에러가 발생하지 않아야 한다.")
    @Test
    void save() {
        String email = "abc1234@test.com";
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
    }

    @DisplayName("중복된 이메일을 이용하여 회원 정보를 등록할 때, 에러가 발생해야 한다.")
    @Test
    void saveDuplicatedEmail() {
        String email = this.user.getEmail();
        String nickname = "테스터";
        String password = "1234";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            userRepository.save(user);
        });
    }

    @DisplayName("이메일이 null인 회원 정보를 등록할 때, 에러가 발생해야 한다.")
    @Test
    void saveNullEmail() {
        String email = null;
        String nickname = "테스터";
        String password = "1234";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            userRepository.save(user);
        });
    }

    @DisplayName("닉네임이 null인 회원 정보를 등록할 때, 에러가 발생해야 한다.")
    @Test
    void saveNullNickname() {
        String email = "test@test.com";
        String nickname = null;
        String password = "1234";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            userRepository.save(user);
        });
    }

    @DisplayName("비밀번호가 null인 회원 정보를 등록할 때, 에러가 발생해야 한다.")
    @Test
    void saveNullPassword() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = null;
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            userRepository.save(user);
        });
    }

    @DisplayName("조회한 회원 목록의 크기는 현재 등록된 회원 목록의 크기와 같아야 한다.")
    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(INIT_SIZE_OF_USERS + 1);
    }

    @DisplayName("id를 이용하여 등록된 회원의 정보는 조회할 수 있어야 한다.")
    @Test
    void findById() {
        long id = user.getId();
        String email = user.getEmail();
        String nickname = user.getNickname();
        String password = user.getPassword();
        LocalDateTime createdAt = user.getCreatedAt();
        LocalDateTime updatedAt = user.getUpdatedAt();

        User foundUser = userRepository.findById(id).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        assertThat(foundUser.getNickname()).isEqualTo(nickname);
        assertThat(foundUser.getPassword()).isEqualTo(password);
        assertThat(foundUser.getCreatedAt()).isEqualTo(createdAt);
        assertThat(foundUser.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @DisplayName("이메일을 이용하여 등록한 회원의 정보를 조회할 수 있어야 한다.")
    @Test
    void findByEmail() {
        String email = user.getEmail();
        String nickname = user.getNickname();
        String password = user.getPassword();
        LocalDateTime createdAt = user.getCreatedAt();
        LocalDateTime updatedAt = user.getUpdatedAt();

        User foundUser = userRepository.findByEmail(email).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        assertThat(foundUser.getNickname()).isEqualTo(nickname);
        assertThat(foundUser.getPassword()).isEqualTo(password);
        assertThat(foundUser.getCreatedAt()).isEqualTo(createdAt);
        assertThat(foundUser.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @DisplayName("중복되지 않은 이메일을 이용하여 회원 정보를 수정할 때, id와 회원가입 일자를 제외한 정보는 업데이트되야 한다.")
    @Test
    void update() {
        String email = "abc1234@test.com";
        String nickname = "ABC";
        String password = "abcd";
        LocalDateTime updatedAt = LocalDateTime.now();
        User userForUpdate = User.builder()
                .id(user.getId())
                .email(email)
                .nickname(nickname)
                .password(password)
                .updatedAt(updatedAt)
                .build();

        userRepository.update(userForUpdate);

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        assertThat(foundUser.getNickname()).isEqualTo(nickname);
        assertThat(foundUser.getPassword()).isEqualTo(password);
        assertThat(foundUser.getCreatedAt()).isEqualTo(user.getCreatedAt());
        assertThat(foundUser.getUpdatedAt()).isEqualTo(updatedAt);
    }

}
