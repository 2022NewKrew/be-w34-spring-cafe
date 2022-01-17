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
        User user = UserSignUpRequest.getUserFromNoDbIndex(request);
        userRepository.save(user);
    }

    public User findUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElse(null);
        if (user != null && user.getPassword().equals(password)) {  // TODO: 여기도 exception으로 처리하기
            return user;
        }
        return null;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public boolean updateUser(int id, UserUpdateRequest request) {
        User user = userRepository.findById(id).get();
        if (!user.getPassword().equals(request.getPassword())) {
            return false;
        }

        user.update(request.getPassword(), request.getName(), request.getEmail());
        userRepository.update(user);
        return true;
    }

}
