package com.kakao.cafe.user.application.port.out;

import com.kakao.cafe.user.domain.UserAccount;

import java.util.List;
import java.util.Optional;

public interface LoadUserAccountPort {

    Optional<UserAccount> findById(Long id);

    Optional<UserAccount> findByEmail(String email);

    List<UserAccount> findAll();
}
