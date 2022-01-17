package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.Profile;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<Profile> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    List<Profile> findAll();
}
