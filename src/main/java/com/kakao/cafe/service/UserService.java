package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.dto.user.UpdateUserDto;
import com.kakao.cafe.util.exception.NotFoundException;
import com.kakao.cafe.util.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(CreateUserDto createUserDto) {
        if (checkDuplicateUserId(createUserDto.getUserId())) {
            throw new IllegalArgumentException("이미 등록된 사용자 입니다.");
        }

        User user = User.builder()
                .userId(createUserDto.getUserId())
                .password(createUserDto.getPassword())
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .build();

        userRepository.insert(user);
    }

    public ShowUserDto findProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("등록되지 않은 사용자 입니다."));

        return new ShowUserDto(user);
    }

    public ShowUserDto updateProfile(String userId, UpdateUserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("등록되지 않은 사용자 입니다."));

        if (!userDto.getPassword().equals(user.getPassword())) {
            throw new UnAuthorizedException("현재 비밀번호가 일치하지 않습니다.");
        }

        String newPassword = (userDto.getNewPassword().equals("")) ? userDto.getPassword() : userDto.getNewPassword();

        User editUser = User.builder()
                .userId(userId)
                .password(newPassword)
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();

        return new ShowUserDto(userRepository.update(userId, editUser));

    }

    public List<ShowUserDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(ShowUserDto::new)
                .collect(Collectors.toList());
    }

    public ShowUserDto login(LoginDto loginDto) {
        User user = userRepository.findById(loginDto.getUserId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 아이디 입니다."));

        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new UnAuthorizedException("비밀번호가 일치하지 않습니다.");
        }

        return new ShowUserDto(user);
    }

    private boolean checkDuplicateUserId(String userId) {
        return userRepository.findById(userId).isPresent();
    }
}
