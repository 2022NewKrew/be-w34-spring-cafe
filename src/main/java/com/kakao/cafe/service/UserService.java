package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.AlreadyExistUserException;
import com.kakao.cafe.exception.IncorrectLoginPasswordException;
import com.kakao.cafe.exception.LoginUserNotFoundException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository jdbcUserRepositoryImpl) {
        this.userRepository = jdbcUserRepositoryImpl;
    }

    @Transactional
    public void createUser(UserDto.CreateUserProfileRequest createUserProfileRequest) throws AlreadyExistUserException {
        userRepository.findByUserId(createUserProfileRequest.getUserId()).ifPresent(m -> {
            throw new AlreadyExistUserException("Already Exist User (user id: " + createUserProfileRequest.getUserId() + ")");
        });

        User user = createUserProfileRequest.toUserEntity();
        userRepository.save(user);
    }

    public List<UserDto.UserProfileResponse> readUsers() {
        return userRepository.findAll().stream()
                .map(UserDto.UserProfileResponse::of)
                .collect(Collectors.toList());
    }

    public UserDto.UserProfileResponse readUser(String userId) throws UserNotFoundException {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("Not Found User (user id: " + userId + ")"));
        return UserDto.UserProfileResponse.of(user);
    }

    @Transactional
    public void updateUser(String userId, UserDto.UpdateUserProfileRequest updateUserProfileRequest) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Not Found User (user id: " + userId + ")"));

        if (!user.isCorrectPassword(updateUserProfileRequest.getOriginPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        user.updateUserProfile(updateUserProfileRequest.toUserEntity(userId));
        userRepository.save(user);
    }

    public UserDto.UserSessionDto authenticateUser(UserDto.LoginDto loginDto) {
        User user = userRepository.findByUserId(loginDto.getUserId())
                .orElseThrow(() -> new LoginUserNotFoundException("Not Found User (user id: " + loginDto.getUserId() + ")"));

        if (!user.isCorrectPassword(loginDto.getPassword())) {
            throw new IncorrectLoginPasswordException("Password is incorrect");
        }

        return UserDto.UserSessionDto.of(user);
    }

    public void validateAuthForUpdateUser(String loginUser, String updateUserId) throws AccessDeniedException {
        if (!loginUser.equals(updateUserId)) {
            throw new AccessDeniedException("해당 사용자에게 수정 권한이 없습니다.");
        }
    }
}
