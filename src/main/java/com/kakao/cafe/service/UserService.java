package com.kakao.cafe.service;

import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    private final UserRepository userRepository;

    public void create(Create createDto) {
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
}
