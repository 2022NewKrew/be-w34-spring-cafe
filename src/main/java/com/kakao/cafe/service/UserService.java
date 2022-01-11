package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreateRequest;
import com.kakao.cafe.dto.UserListResponse;
import com.kakao.cafe.dto.UserProfileResponse;
import com.kakao.cafe.repository.InMemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository = new InMemoryUserRepository();

    public Long signUp(UserCreateRequest userDTO) {
        User user = userDTO.toEntity();
        validateDuplicateUserId(user);
        return userRepository.save(user).getId();
    }

    private void validateDuplicateUserId(User user){
        userRepository.findByNickname(user.getNickname())
                .ifPresent(u -> {
                    throw new IllegalStateException("가입할 수 없는 Nickname 입니다.");
                });
    }

    public List<UserListResponse> findAllUsers() {
        return userRepository.findAll().stream().map(UserListResponse::new).collect(Collectors.toList());
    }

    public UserProfileResponse findOneUser(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(IllegalArgumentException::new);
        return new UserProfileResponse(user);
    }
    public UserProfileResponse findOneUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new UserProfileResponse(user);
    }
}
