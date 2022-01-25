package com.kakao.cafe.service;

import com.kakao.cafe.domain.SessionUser;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exceptions.WrongPasswordException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.request.LoginRequest;
import com.kakao.cafe.response.ProfileResponse;
import com.kakao.cafe.response.UserListResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public List<UserListResponse> getUserList() {
        return userRepository.findAll()
                .stream()
                .map(UserListResponse::of)
                .collect(Collectors.toList());
    }

    public ProfileResponse getProfile(int id) {
        User user = userRepository.findById(id);
        return ProfileResponse.of(user);
    }

    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId());
        if (!user.getPassword().equals(request.getPassword())) {
            throw new WrongPasswordException("비밀번호가 잘못되었습니다");
        }
        return SessionUser.from(user);
    }
}
