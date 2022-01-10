package com.kakao.cafe.infra.user;

import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserAccountMemoryRepositoryImpl implements UserAccountRepository {

    private final Map<Long, UserAccount> userAccountMap;
    private static Long id = 0L;

    public UserAccountMemoryRepositoryImpl() {
        this.userAccountMap = new HashMap<>();
    }

    @Override
    public Optional<UserAccount> save(UserAccount userAccount) {
        UserAccount account = UserAccount.builder()
                        .userAccountId(id)
                        .username(userAccount.getUsername())
                        .password(userAccount.getPassword())
                        .email(userAccount.getEmail())
                        .createdAt(userAccount.getCreatedAt()).build();
        userAccountMap.put(id++, account);

        return Optional.of(account);
    }

    @Override
    public Optional<UserAccount> find(Long id) {
        UserAccount userAccount = userAccountMap.get(id);
        return Optional.of(userAccount);
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
