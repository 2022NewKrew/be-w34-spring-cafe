package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository {

    UserAccount save(UserAccount userAccount);

    Optional<UserAccount> findById(Long id);

    Optional<UserAccount> findByEmail(String email);

    List<UserAccount> findAll();

    void update(UserAccount userAccount);

    void delete(Long id);

    void deleteAll();
}
