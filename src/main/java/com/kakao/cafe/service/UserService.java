package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.dto.UserDTO.Update;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.UserAlreadyExistsException;
import com.kakao.cafe.error.exception.UserInvalidAuthInfoException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.AuthInfo;
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
        if (userRepository.findUserByUid(createDto.getUid()).isPresent()) {
            throw new UserAlreadyExistsException(ErrorCode.ALREADY_EXISTS, createDto.getUid());
        }

        User user = User.of(createDto.getUid(), createDto.getPassword(),
            createDto.getName(), createDto.getEmail());

        userRepository.save(user);
        logger.info("User Created : " + user);
    }

    public void update(AuthInfo authInfo, Update updateDTO) {
        userRepository.update(authInfo.getUid(), updateDTO.getName(), updateDTO.getEmail());
    }

    public List<UserDTO.Result> readAll() {
        return userRepository.findAllUsers().stream()
            .map(Result::from)
            .collect(Collectors.toUnmodifiableList());
    }

    public UserDTO.Result readByUid(String uid) {
        Optional<User> foundUser = userRepository.findUserByUid(uid);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(ErrorCode.NOT_FOUND, uid);
        }

        return Result.from(foundUser.get());
    }
}
