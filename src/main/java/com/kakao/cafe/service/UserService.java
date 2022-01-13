package com.kakao.cafe.service;

import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreateRequest;
import com.kakao.cafe.dto.UserProfileResponse;
import com.kakao.cafe.dto.UserUpdateRequest;
import com.kakao.cafe.repository.InMemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserCreateRequest userDTO) {
        User user = userDTO.toEntity();
        validateDuplicateUserId(user);
        userRepository.save(user);
    }

    public List<UserProfileResponse> findAllUsers() {
        return userRepository.findAll().stream().map(UserProfileResponse::new).collect(Collectors.toList());
    }

    public UserProfileResponse findOneUser(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(IllegalArgumentException::new);
        return new UserProfileResponse(user);
    }
    public UserProfileResponse findOneUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new UserProfileResponse(user);
    }

    public void updateUser(UserUpdateRequest userDTO) {
        User user = userRepository.findByNickname(userDTO.getNickname()).orElseThrow(IllegalArgumentException::new);
        validateEqualPassword(user, userDTO);

        User updateUser = User.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .password(userDTO.getNewPassword())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();

        userRepository.update(updateUser);
    }

    private void validateDuplicateUserId(User user){
        userRepository.findByNickname(user.getNickname())
                .ifPresent(u -> {
                    throw new IllegalStateException("가입할 수 없는 Nickname 입니다.");
                });
    }
    private void validateEqualPassword(User user, UserUpdateRequest userDTO){
        if(!user.getPassword().equals(userDTO.getOriginPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
