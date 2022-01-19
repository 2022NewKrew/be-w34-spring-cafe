package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.dto.UserDTO.Update;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.UserAlreadyExistsException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Transactional
    public void create(Create createDto) {
        if (userRepository.findUserByUid(createDto.getUid()).isPresent()) {
            throw new UserAlreadyExistsException(ErrorCode.ALREADY_EXISTS, createDto.getUid());
        }

        User user = User.builder()
            .uid(createDto.getUid())
            .password(createDto.getPassword())
            .name(createDto.getName())
            .email(createDto.getEmail())
            .createdAt(LocalDateTime.now())
            .build();

        userRepository.save(user);
        logger.info("User Created : {}", user);
    }

    @Transactional
    public void update(AuthInfo authInfo, Update updateDTO) {
        Optional<User> foundUser = userRepository.findUserByUid(authInfo.getUid());
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(ErrorCode.NOT_FOUND, authInfo.getUid());
        }
        if (!foundUser.get().matchPassword(updateDTO.getPassword())) {
            throw new AuthInvalidPasswordException(ErrorCode.AUTHENTICATION_INVALID,
                authInfo.getUid());
        }

        userRepository.update(authInfo.getUid(), updateDTO.getName(), updateDTO.getEmail());
        logger.info("User Modified [UID : {}] [Name : {}] [Email : {}", authInfo.getUid(),
            updateDTO.getName(), updateDTO.getEmail());
    }

    @Transactional(readOnly = true)
    public List<Result> readAll() {
        List<User> users = userRepository.findAllUsers();
        logger.info("Read All Users {}", users);
        return users.stream()
            .map(Result::from)
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public Result readByUid(String uid) {
        Optional<User> foundUser = userRepository.findUserByUid(uid);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(ErrorCode.NOT_FOUND, uid);
        }

        logger.info("Read User by [UID : {}] :: {}", uid, foundUser.get());
        return Result.from(foundUser.get());
    }
}
