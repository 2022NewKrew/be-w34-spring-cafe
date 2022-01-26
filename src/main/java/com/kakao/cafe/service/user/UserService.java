package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.UserInfo;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.exception.UserDuplicateException;
import com.kakao.cafe.exception.UserNotRegisteredException;
import com.kakao.cafe.repository.user.DbUserRepository;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import com.kakao.cafe.web.dto.user.UserResponseDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(DbUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userSingUp(UserCreateRequestDto userCreateRequestDto) {
        if (userRepository.read(userCreateRequestDto.getUserId()).isPresent()) {
            throw new UserDuplicateException("User Duplicated Error", ErrorCode.USER_ID_DUPLICATION);
        }
        userRepository.create(UserInfo.builder()
                .userId(userCreateRequestDto.getUserId())
                .password(userCreateRequestDto.getPassword())
                .name(userCreateRequestDto.getName())
                .email(userCreateRequestDto.getEmail())
                .build());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.readAll().stream().map(userInfo ->
                UserResponseDto.builder()
                        .userId(userInfo.getUserId())
                        .email(userInfo.getEmail())
                        .signUpDate(userInfo.getSignUpDate())
                        .name(userInfo.getName())
                        .build()).collect(Collectors.toList());
    }

    public UserResponseDto findById(String id) {
        Optional<UserInfo> userInfoOptional = userRepository.read(id);
        UserInfo userInfo = userInfoOptional.orElseThrow(() -> new UserNotRegisteredException("User Not Registered Error", ErrorCode.USER_NOT_REGISTERED));
        return UserResponseDto.builder()
                .userId(userInfo.getUserId())
                .email(userInfo.getEmail())
                .signUpDate(userInfo.getSignUpDate())
                .name(userInfo.getName())
                .password(userInfo.getPassword())
                .build();
    }

    public String userLogin(String userId, String password, HttpSession session) {
        UserResponseDto target = findById(userId);

        if (target != null && target.getPassword().equals(password)) {
            session.setAttribute("sessionUser", target);
            return "redirect:/";
        }
        throw new InvalidPasswordException("Invalid Password", ErrorCode.INVALID_PASSWORD);
    }
}
