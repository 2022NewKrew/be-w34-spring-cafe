package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.FindUserDto;
import java.util.List;
import java.util.Optional;

public interface UserUseCase {

    UserId join(CreateUserDto createUserDto);

    List<User> getAll();

    Optional<User> find(FindUserDto findUserDto);

}
