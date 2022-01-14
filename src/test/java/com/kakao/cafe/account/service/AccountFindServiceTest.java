package com.kakao.cafe.account.service;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountFindServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountFindService accountFindService;

    private final AccountDto givenAccountDto =
            AccountDto.builder()
                    .userId("id")
                    .name("name")
                    .password("password")
                    .email("email@aaa.com").build();

    private final Account givenAccount = givenAccountDto.toEntity();

    @Test
    @DisplayName("회원 검색 성공")
    void test1() {
        // given
        given(accountFindService.getProfile("id")).willReturn(givenAccountDto);

        // when
        AccountDto foundAccountDto = accountFindService.getProfile("id");

        // then
        assertThat(givenAccount.getUserId()).isEqualTo(foundAccountDto.getUserId());
        assertThat(givenAccount.getName()).isEqualTo(foundAccountDto.getName());
        assertThat(givenAccount.getPassword()).isEqualTo(foundAccountDto.getPassword());
        assertThat(givenAccount.getEmail()).isEqualTo(foundAccountDto.getEmail());
    }

    @Test
    @DisplayName("회원 리스트 조회 성공")
    void test2() {
        // given
        List<AccountDto> givenAccountDtoList = new ArrayList<>();
        givenAccountDtoList.add(givenAccountDto);

        given(accountFindService.getAccountList()).willReturn(givenAccountDtoList);

        // when
        List<AccountDto> foundAccountList = accountFindService.getAccountList();

        // then
        assertThat(foundAccountList.size()).isEqualTo(givenAccountDtoList.size());
    }
}
