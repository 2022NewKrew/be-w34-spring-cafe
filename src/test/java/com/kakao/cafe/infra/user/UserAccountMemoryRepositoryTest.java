package com.kakao.cafe.infra.user;

import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountMemoryRepositoryTest {

    @Autowired
    UserAccountRepository accountRepository;

    UserAccount testUser1 = UserAccount.builder()
            .email("edward@kakao.com")
            .password("1234")
            .username("edward")
            .createdAt(LocalDate.now())
            .build();

    UserAccount testUser2 = UserAccount.builder()
            .email("peach@kakao.com")
            .password("4321")
            .username("peach")
            .createdAt(LocalDate.now())
            .build();

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("저장 테스트")
    void save() {

        UserAccount result = accountRepository.save(testUser1);

        System.out.println(result);
        System.out.println(testUser1);
        Assertions.assertThat(result).isEqualTo(testUser1);
    }

    @Test
    @DisplayName("찾기 테스트")
    void find() {

        UserAccount saved = accountRepository.save(testUser1);

        UserAccount found = accountRepository.findById(saved.getUserAccountId()).orElseThrow(IllegalArgumentException::new);

        Assertions.assertThat(found.getUserAccountId()).isEqualTo(saved.getUserAccountId());
    }

    @Test
    @DisplayName("전부 찾아오기")
    void findAll() {
        accountRepository.save(testUser1);
        accountRepository.save(testUser2);

        List<UserAccount> found = accountRepository.findAll();

        Assertions.assertThat(found.size()).isEqualTo(2);
    }
}