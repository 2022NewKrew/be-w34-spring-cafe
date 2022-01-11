package com.kakao.cafe.repository;

import com.kakao.cafe.User;
import com.kakao.cafe.dto.CreateUserDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(CreateUserDto createUserDto);

    Optional<User> findById(UUID id);

    List<User> getAll();
}
