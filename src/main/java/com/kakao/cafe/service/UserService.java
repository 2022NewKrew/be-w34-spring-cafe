package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.IllegalUserInputException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserRequest;
import com.kakao.cafe.web.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userFromUserDTO(UserRequest userRequest) {
        return User.builder()
                .userId(userRequest.getUserId())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .registerDate(userRequest.getRegisterDate())
                .build();
    }

    public UserResponse userResponseDTOFromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .password(user.getPassword())
                .userId(user.getUserId())
                .email(user.getEmail())
                .registerDate(user.getRegisterDate())
                .build();
    }

    public void createUser(UserRequest userRequest) {
        Optional<User> user = userRepository.findByUserId(userRequest.getUserId());
        if (user.isEmpty())
            userRepository.create(userFromUserDTO(userRequest));
        else
            throw new IllegalUserInputException("이미 존재하는 아이디입니다.");
    }

    public UserResponse getUserByUserId(String userId) throws IllegalUserInputException {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userResponseDTOFromUser(user);
        }
        throw new IllegalUserInputException("해당 아이디의 유저가 없습니다.");
    }

    public int getUserListSize() {
        return userRepository.getUserList().size();
    }

    public List<UserResponse> getUserDTOList() {
        return userRepository.getUserList().stream().map(this::userResponseDTOFromUser).collect(Collectors.toList());
    }

    public Optional<UserResponse> getSessionUserDTO(String userId, String password) {
        Optional<User> user = userRepository.getSessionUser(userId, password);
        if (user.isEmpty())
            return Optional.empty();
        return Optional.of(userResponseDTOFromUser(user.get()));
    }

}
