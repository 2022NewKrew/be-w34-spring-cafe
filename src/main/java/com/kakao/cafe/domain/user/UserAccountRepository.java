package com.kakao.cafe.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository {

    Optional<UserAccount> save(UserAccount userAccount);

    Optional<UserAccount> find(Long id);

    List<UserAccount> findAll();

    void delete(Long id);

}
