package com.kakao.cafe.user.application.port.out;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import java.util.Optional;

public interface LoadUserPort {

    Optional<User> load(UserId userId);

    List<User> loadAll();

}
