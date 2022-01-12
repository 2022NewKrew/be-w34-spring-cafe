package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.UserAlreadyExistsException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public void create(Create createDto) {
        if (userRepository.findUserByUserId(createDto.getUserId()).isPresent()) {
            throw new UserAlreadyExistsException(ErrorCode.ALREADY_EXISTS, createDto.getUserId());
        }

        User user = User.of(createDto.getUserId(), createDto.getPassword(),
            createDto.getName(), createDto.getEmail());

        userRepository.add(user);
        logger.info("User Created : " + user);
    }

    public List<UserDTO.Result> readAll() {
        return userRepository.findAllUsers().stream()
            .map(Result::from)
            .collect(Collectors.toUnmodifiableList());
    }

    public UserDTO.Result readByUserId(String userId) {
        Optional<User> foundUser = userRepository.findUserByUserId(userId);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(ErrorCode.NOT_FOUND, userId);
        }

        return Result.from(foundUser.get());
    }
}
