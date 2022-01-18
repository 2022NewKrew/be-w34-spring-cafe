package com.kakao.cafe.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.persistence.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql("classpath:jdbc/schema.sql")
class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void init(@Autowired JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepositoryImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("User Repository 저장 후 조회 테스트")
    void saveAndFindAll() {
        // Given
        User user1 = User.builder().uid("uid1").password("pwd1").name("name1")
            .email("email1@test.com").createdAt(LocalDateTime.now())
            .build();
        User user2 = User.builder().uid("uid2").password("pwd2").name("name2")
            .email("email2@test.com").createdAt(LocalDateTime.now())
            .build();
        User user3 = User.builder().uid("uid3").password("pwd3").name("name3")
            .email("email3@test.com").createdAt(LocalDateTime.now())
            .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // When
        List<User> users = userRepository.findAllUsers();

        // Then
        assertThat(users.size())
            .isEqualTo(3);
    }

    @Test
    @DisplayName("User Repository 저장 후 특정 UID 조회 테스트")
    void saveAndFindByUid() {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .createdAt(LocalDateTime.now()).build();

        userRepository.save(user);

        // When
        Optional<User> existUser = userRepository.findUserByUid("uid");
        Optional<User> notExistUser = userRepository.findUserByUid("uid0");

        // Then
        assertThat(existUser)
            .isPresent();
        assertThat(notExistUser)
            .isEmpty();
    }

    @Test
    @DisplayName("User Repository 저장 후 업데이트 테스트")
    void saveAndUpdate() {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .createdAt(LocalDateTime.now()).build();

        userRepository.save(user);

        // When
        userRepository.update("uid", "modified", "modified@test.com");
        Optional<User> modifiedUser = userRepository.findUserByUid("uid");

        // Then
        assertThat(modifiedUser)
            .isPresent();
        assertThat(modifiedUser.get().getName())
            .isEqualTo("modified");
        assertThat(modifiedUser.get().getEmail())
            .isEqualTo("modified@test.com");
    }
}