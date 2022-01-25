package com.kakao.cafe.service;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repo.UserRepository;
import com.kakao.cafe.util.SecurePassword;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;

    UserManager(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Transactional
    @Override
    public void add(
            @NonNull final UserDto userDto,
            @NonNull final String password
    )
    {
        if (userRepository.checkIdExist(userDto.getId())) {
            throw new IllegalStateException("Duplicate id is exist!");
        }
        final boolean result = userRepository.add(new User(
                userDto.getId(),
                password,
                userDto.getName(),
                userDto.getEmail()
        ));

        if (!result) {
            throw new RuntimeException("Failed to insert user!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getList() {
        final List<UserDto> dtos = new ArrayList<>();
        for (User u: userRepository.getList()) {
            dtos.add(UserDto.from(u));
        }

        return Collections.unmodifiableList(dtos);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getDto(@NonNull final String id) throws NoSuchElementException {
        return UserDto.from(get(id));
    }

    @Transactional(readOnly = true)
    @Override
    public User get(@NonNull final String id) throws NoSuchElementException {
        final User user = userRepository.findById(id);
        if (user.isNone()) {
            throw new NoSuchElementException("Not found user - " + id);
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean verifyPassword(@NonNull final String id, @NonNull final String rawPassword) {
        User user;
        try {
            user = get(id);
        } catch (NoSuchElementException e) {
            return false;
        }

        return SecurePassword.verify(user.getPassword(), rawPassword);
    }

    @Transactional
    @Override
    public boolean update(
            @NonNull final UserDto userDto,
            @NonNull final String rawPassword,
            @NonNull final String newRawPassword
    )
    {
        final String id = userDto.getId();
        User user;
        try {
            user = get(id);
        } catch (NoSuchElementException e) {
            return false;
        }

        if (!SecurePassword.verify(user.getPassword(), rawPassword)) {
            return false;
        }

        return userRepository.update(
                userDto.getIdx(),
                new User(id, newRawPassword, userDto.getName(), userDto.getEmail())
        );
    }
}
