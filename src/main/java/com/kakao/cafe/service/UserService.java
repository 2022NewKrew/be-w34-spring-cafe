package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.AlreadyExistUserException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository localUserRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.localUserRepository = userRepository;
    }

    public void createUser(UserDto.CreateUserProfileRequest createUserProfileRequest) throws AlreadyExistUserException {
        localUserRepository.findByUserId(createUserProfileRequest.getUserId()).ifPresent(m -> {
            throw new AlreadyExistUserException("Already Exist User");
        });

        User user = createUserProfileRequest.toUserEntity();
        localUserRepository.save(user);
    }

    public List<UserDto.UserProfileResponse> readUsers() {
        return localUserRepository.findAll().stream()
                .map(UserDto.UserProfileResponse::of)
                .collect(Collectors.toList());
    }

    public UserDto.UserProfileResponse readUser(String userId) throws UserNotFoundException {
        User user = localUserRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("Not Found User"));
        return UserDto.UserProfileResponse.of(user);
    }

}
