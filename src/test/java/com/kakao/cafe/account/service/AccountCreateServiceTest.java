package com.kakao.cafe.account.service;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountCreateServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountCreateService accountCreateService;

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        // given
        AccountDto accountDto =
                AccountDto.builder()
                .userId("id")
                .name("name")
                .password("password")
                .email("email@aaa.com").build();
        Account account = accountDto.toEntity();

        given(accountRepository.save(account)).willReturn(account);
        Account saveAccount = accountRepository.save(account);
        given(accountCreateService.save(accountDto)).willReturn(saveAccount.toDto());

        // when
        AccountDto savedAccountDto = accountCreateService.save(accountDto);

        // then
        assertThat(account.getUserId()).isEqualTo(savedAccountDto.getUserId());
        assertThat(account.getName()).isEqualTo(savedAccountDto.getName());
        assertThat(account.getPassword()).isEqualTo(savedAccountDto.getPassword());
        assertThat(account.getEmail()).isEqualTo(savedAccountDto.getEmail());
    }
}
