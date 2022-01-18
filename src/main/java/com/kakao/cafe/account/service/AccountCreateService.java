package com.kakao.cafe.account.service;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.mapper.AccountMapper;
import com.kakao.cafe.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountCreateService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountCreateService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDto save(AccountDto account) {
        return accountMapper.toDto(
                accountRepository.save(accountMapper.toEntity(account)));
    }


}
