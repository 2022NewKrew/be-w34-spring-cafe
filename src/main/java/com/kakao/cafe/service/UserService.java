package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    void add(@NonNull final UserDto userDto, @NonNull final String password);
    List<UserDto> getList();
    UserDto getUser(@NonNull final String id) throws NoSuchElementException;
    User getUserEntity(@NonNull final String id) throws NoSuchElementException;
    boolean verifyUserLogin(@NonNull final String id, @NonNull final String rawPassword);
    boolean updateUser(@NonNull final UserDto userDto, @NonNull final String rawPassword, @NonNull final String newRawPassword);
}
