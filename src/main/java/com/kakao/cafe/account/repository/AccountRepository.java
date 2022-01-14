package com.kakao.cafe.account.repository;

import com.kakao.cafe.account.entity.Account;

import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    List<Account> findAll();
    Account findById(String userId);
}
