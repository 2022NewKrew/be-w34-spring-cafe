package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.FindUserDto;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(CreateUserDto createUserDto);

    Optional<User> find(FindUserDto findUserDto);

    List<User> getAll();
}
