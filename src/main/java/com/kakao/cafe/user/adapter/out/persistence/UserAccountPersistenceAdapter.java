package com.kakao.cafe.user.adapter.out.persistence;

import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.application.port.out.SaveUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserAccountPersistenceAdapter implements LoadUserAccountPort, SaveUserAccountPort {

    private final UserAccountRepository userAccountRepository;

    public UserAccountPersistenceAdapter(@Qualifier("jdbc-account-db") UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        return userAccountRepository.findById(id);
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
