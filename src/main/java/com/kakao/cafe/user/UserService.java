package com.kakao.cafe.user;

import com.kakao.cafe.user.dto.request.UserRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.repository.UserH2Repository;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserH2Repository userRepository) {
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
