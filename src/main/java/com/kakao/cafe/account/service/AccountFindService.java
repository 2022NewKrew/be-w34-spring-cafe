package com.kakao.cafe.account.service;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountFindService {
    private final AccountRepository accountRepository;

    public AccountFindService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getAccountList() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : accountList) {
            accountDtoList.add(account.toDto());
        }
        return accountDtoList;
    }

    public AccountDto getProfile(String userId) {
        return accountRepository.findById(userId).toDto();
    }


}
