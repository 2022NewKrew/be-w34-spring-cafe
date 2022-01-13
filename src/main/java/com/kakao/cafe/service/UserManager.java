package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.repo.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;

    UserManager(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public void add(
            @NonNull final UserDto userDto,
            @NonNull final String password
    )
    {
        if (userRepository.checkIdExist(userDto.getId())) {
            throw new IllegalStateException("Duplicate id is exist!");
        }
        userRepository.add(new User(
                userDto.getId(),
                password,
                userDto.getName(),
                userDto.getEmail()
        ));
    }

    @Override
    public List<UserDto> getList() {
        final List<UserDto> dtos = new ArrayList<>();
        for (User u: userRepository.getList()) {
            dtos.add(UserDto.from(u));
        }

        return Collections.unmodifiableList(dtos);
    }

    @Override
    public UserDto getUser(@NonNull final String id) throws NoSuchElementException {
        return UserDto.from(getUserEntity(id));
    }

    @Override
    public User getUserEntity(@NonNull final String id) throws NoSuchElementException {
        final User user = userRepository.findById(id);
        if (user.isNone()) {
            throw new NoSuchElementException("Not found user - " + id);
        }
        return user;
    }
}
