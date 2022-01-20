package com.kakao.cafe.users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByUserId(String userId);

    Optional<User> findById(Long id);
    
    List<User> findAll();
}
