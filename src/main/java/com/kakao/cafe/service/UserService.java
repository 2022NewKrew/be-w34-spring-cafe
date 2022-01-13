package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserSignUpRequest;
import com.kakao.cafe.dto.UserUpdateRequest;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserSignUpRequest request) {
        User user = User.fromNoDbIndex(request);
        userRepository.save(user);
    }

    public User profile(int id) {
        return userRepository.findById(id).get();
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public boolean updateUser(int id, UserUpdateRequest request) {
        User user = userRepository.findById(id).get();
        if (!user.getPassword().equals(request.getPassword())) {
            return false;
        }

        user.setName(request.getName());
        user.setPassword(request.getNewPassword());
        user.setEmail(request.getEmail());
        userRepository.update(user);
        return true;
    }
}
