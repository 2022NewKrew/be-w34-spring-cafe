package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.error.UserError;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public void create(Create createDto) {
        if (userRepository.findUserByUserId(createDto.getUserId()).isPresent()) {
            logger.error("User ID : {} {}", createDto.getUserId(),
                UserError.ALREADY_EXISTS.getMessage());

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                UserError.ALREADY_EXISTS.getMessage());
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
            logger.error("User ID : {} {}", userId, UserError.NOT_FOUND.getMessage());

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                UserError.NOT_FOUND.getMessage());
        }

        return Result.from(foundUser.get());
    }
}
