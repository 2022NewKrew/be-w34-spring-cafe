package com.kakao.cafe.persistence.user;

import com.kakao.cafe.exception.IdNotFoundException;
import com.kakao.cafe.user.domain.UserAccount;
import com.kakao.cafe.user.domain.UserAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserAccountJdbcRepositoryTest {

    @Autowired
    @Qualifier("jdbc-account-db")
    UserAccountRepository accountRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    UserAccount testUser1;

    UserAccount testUser2;

    UserAccount expected1 = UserAccount.builder()
            .userAccountId(1L)
            .email("edward@kakao.com")
            .password("1234")
            .username("edward")
            .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
            .build();

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("truncate table user_account");

        testUser1 = UserAccount.builder()
                .email("edward@kakao.com")
                .password("1234")
                .username("edward")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .build();

        testUser2 = UserAccount.builder()
                .email("peach@kakao.com")
                .password("4321")
                .username("peach")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .build();
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("alter table user_account alter column user_account_id restart with 1");
    }

    @Test
    @Transactional
    void save() {
        UserAccount result = accountRepository.save(testUser1);

        Assertions.assertThat(result).isEqualTo(expected1);
    }

    @Test
    @Transactional
    void findById() {
        UserAccount saved = accountRepository.save(testUser1);

        UserAccount found = accountRepository.findById(saved.getUserAccountId())
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));

        Assertions.assertThat(found.getUserAccountId()).isEqualTo(saved.getUserAccountId());
    }

    @Test
    @Transactional
    void findAll() {
        accountRepository.save(testUser1);
        accountRepository.save(testUser2);

        List<UserAccount> found = accountRepository.findAll();

        Assertions.assertThat(found.size()).isEqualTo(2);
    }
}