package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.AccessDeniedException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UpdateDTO;
import com.kakao.cafe.user.factory.UserFactory;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("유저가 존재하지 않습니다."));
    }

    public void updateUser(UpdateDTO updateDTO, String sessionUserId) {
        checkLoginUserId(updateDTO.getUserId(), sessionUserId);
        User user = findByUserId(updateDTO.getUserId());
        user.validateEqualsPassword(updateDTO.getPassword());
        userRepository.save(UserFactory.toUser(updateDTO));
    }

    public void login(String userId, String password) {
        User user = findByUserId(userId);
        user.validateEqualsPassword(password);
    }

    private void checkLoginUserId(String updatedUserId, String sessionUserId) {
        if (sessionUserId == null || !sessionUserId.equals(updatedUserId)) {
            throw new AccessDeniedException("해당 유저에 대한 수정권한이 없습니다.");
        }
    }
}
