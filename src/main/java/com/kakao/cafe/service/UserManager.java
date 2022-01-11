package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.Users;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManager implements UserService {
    private final Users users = new Users();

    @Override
    public void add(
            @NonNull final UserDto userDto,
            @NonNull final String password
    )
    {
        if (users.checkIdExist(userDto.getId())) {
            throw new IllegalStateException("Duplicate id is exist!");
        }
        users.add(new User(
                        userDto.getId(),
                        password,
                        userDto.getName(),
                        userDto.getEmail()
                ));
    }

    @Override
    public List<UserDto> getList() {
        final List<UserDto> dtos = new ArrayList<>();
        for (User u: users.getList()) {
            dtos.add(UserDto.from(u));
        }

        return Collections.unmodifiableList(dtos);
    }

    @Override
    public UserDto getUser(@NonNull final String id) throws NoSuchElementException {
        final User user = users.find(id);
        if (user.isNone()) {
            throw new NoSuchElementException("Not found user - " + id);
        }
        return UserDto.from(user);
    }
}
