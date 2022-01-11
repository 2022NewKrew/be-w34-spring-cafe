package com.kakao.cafe.infra.user;

import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserAccountMemoryRepositoryImpl implements UserAccountRepository {

    private final Map<Long, UserAccount> userAccountMap;

    public UserAccountMemoryRepositoryImpl() {
        this.userAccountMap = new HashMap<>();
    }

    @Override
    public Optional<UserAccount> save(UserAccount userAccount) {
        userAccountMap.put(userAccount.getUserAccountId(), userAccount);

        return Optional.of(userAccount);
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        UserAccount userAccount = userAccountMap.get(id);
        return Optional.of(userAccount);
    }
    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return userAccountMap.values().stream()
                .filter(v -> v.checkEmail(email))
                .findAny();
    }

    @Override
    public List<UserAccount> findAll() {
        return new ArrayList<>(userAccountMap.values());
    }

    @Override
    public void delete(Long id) {
        userAccountMap.remove(id);
    }

}
