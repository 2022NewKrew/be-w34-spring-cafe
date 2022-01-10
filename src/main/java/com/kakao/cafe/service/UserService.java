package com.kakao.cafe.service;

import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
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

    private static final String ALREADY_EXISTS_ID_MESSAGE = "중복된 사용자 ID 입니다.";
    private static final String NOT_FOUND_USER_MESSAGE = "입력한 사용자를 찾을 수 없습니다.";

    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    private final UserRepository userRepository;

    public void create(Create createDto) {
        if (userRepository.findUserByUserId(createDto.getUserId()).isPresent()) {
            logger.error("id : " + createDto.getUserId() + ALREADY_EXISTS_ID_MESSAGE);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ALREADY_EXISTS_ID_MESSAGE);
        }
        User user = User.of(createDto.getUserId(), createDto.getPassword(),
            createDto.getName(), createDto.getEmail());

        userRepository.addUser(user);
        logger.info("User Created : " + user);
    }

    public List<UserDTO.Result> readAll() {
        return userRepository.findUsers().stream()
            .map(Result::from)
            .collect(Collectors.toList());
    }

    public UserDTO.Result readByUserId(String userId) {
        Optional<User> foundUser = userRepository.findUserByUserId(userId);
        if (foundUser.isEmpty()) {
            logger.error("id : " + userId + " " + NOT_FOUND_USER_MESSAGE);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_USER_MESSAGE);
        }
        
        return Result.from(foundUser.get());
    }
}
