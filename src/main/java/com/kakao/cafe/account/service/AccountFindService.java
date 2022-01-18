package com.kakao.cafe.account.service;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.mapper.AccountMapper;
import com.kakao.cafe.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountFindService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountFindService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<AccountDto> getAccountList() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    public AccountDto getProfile(String userId) {
        return accountMapper.toDto(accountRepository.findById(userId));
    }


}
