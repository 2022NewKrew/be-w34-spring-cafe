package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(CreateUserDto createUserDto);

    Optional<User> findById(UUID userId);

    List<User> getAll();
}
