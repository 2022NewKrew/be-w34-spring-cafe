package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryJdbcImplTest {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryJdbcImplTest(DataSource dataSource) {
        userRepository = new UserRepositoryJdbcImpl(dataSource);
    }

    @Test
    @DisplayName("정상적인 회원가입 확인")
    public void saveUserTest() {
        User user = User.builder()
                .userId("test")
                .password("test")
                .name("테스트")
                .email("test@kafe.com").build();

        assertThat(userRepository.saveUser(user)).isTrue();
    }

    @Test
    @DisplayName("중복 회원가입을 시도했을 때 확인")
    public void saveDuplicatedUserTest() {
        User user1 = User.builder()
                .userId("test")
                .password("test1")
                .name("테스트1")
                .email("test1@kafe.com").build();

        User user2 = User.builder()
                .userId("test")
                .password("test2")
                .name("테스트2")
                .email("test2@kafe.com").build();

        assertThat(userRepository.saveUser(user1)).isTrue();
        assertThat(userRepository.saveUser(user2)).isFalse();
    }

    @Test
    @DisplayName("admin 계정 ID로 검색 확인")
    public void findAdminAccountByUserId() {
        Optional<User> admin = userRepository.findUserByUserId("admin");
        assertThat(admin.isPresent()).isTrue();
    }

    @Test
    @DisplayName("admin 계정 ID 및 비밀번호로 검색 확인")
    public void findAdminAccountByLoginInfo() {
        Optional<User> admin = userRepository.findUserByLoginInfo("admin", "admin");
        assertThat(admin.isPresent()).isTrue();
    }

    @Test
    @DisplayName("ID가 jynah92인 계정 삭제")
    public void deleteAdminAccount() {
        assertThat(userRepository.deleteUser("jynah92", "carpediem92")).isTrue();
    }
}