package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository {

    Optional<UserAccount> save(UserAccount userAccount);

    Optional<UserAccount> findById(Long id);

    Optional<UserAccount> findByEmail(String email);

    List<UserAccount> findAll();

    void delete(Long id);

}
