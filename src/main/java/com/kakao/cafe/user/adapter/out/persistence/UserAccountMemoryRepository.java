package com.kakao.cafe.user.adapter.out.persistence;

import com.kakao.cafe.user.domain.UserAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Qualifier("simple-account-db")
public class UserAccountMemoryRepository implements UserAccountRepository {

    private final Map<Long, UserAccount> userAccountMap;

    public UserAccountMemoryRepository() {
        this.userAccountMap = new ConcurrentHashMap<>();
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        userAccountMap.put(userAccount.getUserAccountId(), userAccount);

        return userAccount;
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
    public void update(UserAccount userAccount) {
        userAccountMap.put(userAccount.getUserAccountId(), userAccount);
    }

    @Override
    public void delete(Long id) {
        userAccountMap.remove(id);
    }

    @Override
    public void deleteAll() {
        userAccountMap.clear();
    }
}
