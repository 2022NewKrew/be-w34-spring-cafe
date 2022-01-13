package com.kakao.cafe.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.persistence.model.User;
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
        User user1 = User.of("uid1", "password1", "name1", "email1@test.com");
        User user2 = User.of("uid2", "password2", "name2", "email2@test.com");
        User user3 = User.of("uid3", "password3", "name3", "email3@test.com");
        User user4 = User.of("uid4", "password4", "name4", "email4@test.com");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        // When
        List<User> users = userRepository.findAllUsers();

        // Then
        assertThat(users.size())
            .isEqualTo(4);
    }

    @Test
    @DisplayName("User Repository 저장 후 특정 UID 조회 테스트")
    void saveAndFindByUid() {
        // Given
        User user1 = User.of("uid1", "password1", "name1", "email1@test.com");

        userRepository.save(user1);

        // When
        Optional<User> existUser = userRepository.findUserByUid("uid1");
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
        User user1 = User.of("uid1", "password1", "name1", "email1@test.com");

        userRepository.save(user1);

        // When
        userRepository.update("uid1", "modified", "modified@test.com");
        Optional<User> modifiedUser = userRepository.findUserByUid("uid1");

        // Then
        assertThat(modifiedUser)
            .isPresent();
        assertThat(modifiedUser.get().getName())
            .isEqualTo("modified");
        assertThat(modifiedUser.get().getEmail())
            .isEqualTo("modified@test.com");
    }
}