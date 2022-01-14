package com.kakao.cafe.account.repository;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.exception.custom.DuplicateException;
import com.kakao.cafe.exception.custom.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;


class AccountRepositoryTest {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private AccountDto accountDto;

    @BeforeEach
    void createDto() {
        accountDto = AccountDto.builder()
                .userId("id")
                .name("name")
                .password("password")
                .email("email").build();
    }

    @Test
    @DisplayName("회원 정보 저장")
    void test1() {
        // given
        Account account = accountDto.toEntity();

        // when
        accountRepository.save(account);

        // then
        assertThat(accountRepository.findById(accountDto.getUserId())).isEqualTo(account);
    }

    @Test
    @DisplayName("이미 있는 아이디일시 에러 발생")
    void test2() {
        // given
        Account account = AccountDto.builder()
                .userId("id")
                .name("name")
                .password("password")
                .email("email").build().toEntity();

        // when
        accountRepository.save(accountDto.toEntity());

        // then
        assertThatExceptionOfType(DuplicateException.class).isThrownBy(() -> accountRepository.save(account));
    }

    @Test
    @DisplayName("찾으려는 회원이 없을시 에러")
    void test3() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> accountRepository.findById("nothing"));
    }
}
