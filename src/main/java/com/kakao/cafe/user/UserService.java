package com.kakao.cafe.user;

import com.kakao.cafe.user.dto.request.UserRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserRequest userRequest) {
        userRepository.save(userRequest.toUser());
    }

    public UsersResponse findAll() {
        return UsersResponse.toResponse(userRepository.findAll());
    }

    public UserResponse findById(String userId) {
        return UserResponse.toResponse(userRepository.findById(userId));
    }
}
