package com.kakao.cafe.application.service;

import com.kakao.cafe.application.dto.UserDto;
import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.repository.UserRepository;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(@Qualifier("UserRepositoryJdbcImpl") UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserDto userDto) {
        if (!userRepository.saveUser(modelMapper.map(userDto, User.class))) {
            throw new UserDuplicatedException();
        }
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(Long id) {
        return userRepository.findUserById(id).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto findUserByLoginInfo(String userId, String password, String errorMessage) {
        return userRepository.findUserByLoginInfo(userId, password).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(errorMessage));
    }

    @Override
    public void modifyUser(UserDto userDto) {
        if (!userRepository.modifyUser(modelMapper.map(userDto, User.class))) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void withdrawUser(String userId, String password) {
        if (!userRepository.deleteUser(userId, password)) {
            throw new UserNotFoundException();
        }
    }
}
