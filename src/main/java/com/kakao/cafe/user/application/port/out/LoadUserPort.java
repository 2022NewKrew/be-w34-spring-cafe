package com.kakao.cafe.user.application.port.out;

import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import java.util.Optional;

public interface LoadUserPort {

    Optional<User> loadById(UserId userId);

    Optional<User> loadByEmail(Email email);

    List<User> loadAll();

}
