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
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }

    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("없는 아이디거나 비밀번호가 잘못되었습니다."));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("없는 아이디거나 비밀번호가 잘못되었습니다.");
        }

        return user;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void updateUser(int id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 아이디거나 비밀번호가 잘못되었습니다."));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 잘못되었습니다.");
        }

        user.update(request.getPassword(), request.getName(), request.getEmail());
        userRepository.update(user);
    }

}
