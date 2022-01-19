package com.kakao.cafe.account.repository;

import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.custom.DuplicateException;
import com.kakao.cafe.exception.custom.NotFoundException;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryAccountRepositoryImpl implements AccountRepository {

    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public Account save(Account account) {
        validDuplicationUserId(account.getUserId());
        accounts.put(account.getUserId(), account);
        return account;
    }

    private void validDuplicationUserId(String userId) {
        if (accounts.containsKey(userId)) throw new DuplicateException(ErrorCode.DUPLICATED_USER_ID);
    }

    @Override
    public List<Account> findAll() {
        return accounts.values().stream().collect(Collectors.toList());
    }

    @Override
    public Account findById(String userId) {
        if(!accounts.containsKey(userId)) throw new NotFoundException();
        return accounts.get(userId);
    }

}
