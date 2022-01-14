package com.kakao.cafe.account.service;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountCreateService {
    private final AccountRepository accountRepository;

    public AccountCreateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto save(AccountDto account) {
        return accountRepository
                .save(account.toEntity())
                .toDto();
    }


}
