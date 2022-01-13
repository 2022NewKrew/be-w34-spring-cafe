package com.kakao.cafe.model.service;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.repository.UserRepository;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void registerUser(UserDto userDto) {
        if (!userRepository.saveUser(modelMapper.map(userDto, User.class))) {
            throw new UserDuplicatedException("중복된 회원이 존재합니다.");
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
                .orElseThrow(() -> new UserNotFoundException("입력하신 저장소 ID와 일치하는 회원 정보가 존재하지 않습니다."));
    }

    @Override
    public UserDto findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("입력하신 회원 ID와 일치하는 회원 정보가 존재하지 않습니다."));
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
            throw new UserNotFoundException("해당 회원 정보와 일치하는 회원이 존재하지 않습니다.");
        }
    }

    @Override
    public void withdrawUser(String userId, String password) {
        if (!userRepository.deleteUser(userId, password)) {
            throw new UserNotFoundException("입력하신 회원 ID 및 비밀번호와 일치하는 회원 정보가 존재하지 않습니다.");
        }
    }
}
