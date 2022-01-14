package com.kakao.cafe.account.repository;

import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.custom.DuplicateException;
import com.kakao.cafe.exception.custom.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final List<Account> accountList = new ArrayList<>();

    @Override
    public Account save(Account account) {
        validDuplicationUserId(account.getUserId());
        accountList.add(account);
        return account;
    }

    private void validDuplicationUserId(String userId) {
        for (Account account : accountList) {
            if(account.getUserId().equals(userId)) {
                throw new DuplicateException(ErrorCode.DUPLICATED_USER_ID);
            }
        }
    }

    @Override
    public List<Account> findAll() {
        return accountList;
    }

    @Override
    public Account findById(String userId) {
        for (Account account : accountList) {
            if(account.getUserId().equals(userId)) {
                return account;
            }
        }
        throw new NotFoundException();
    }

}
