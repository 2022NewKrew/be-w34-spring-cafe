package com.kakao.cafe.service;

import com.kakao.cafe.dto.user.ModifyingUserForm;
import com.kakao.cafe.dto.user.UserProfileInfo;
import com.kakao.cafe.dto.user.UserRegistrationForm;
import com.kakao.cafe.dto.user.UserShowForm;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserRegistrationForm userRegistrationForm) {
        User user = new User(userRegistrationForm.getNickName(),
                userRegistrationForm.getEmail(),
                userRegistrationForm.getName(),
                userRegistrationForm.getPassword(),
                LocalDateTime.now());
        userRepository.saveUser(user);
    }

    public List<UserShowForm> getMemberList() {
        List<User> userList = userRepository.findAll();
        AtomicInteger idx = new AtomicInteger(1);
        return userList.stream()
                .map(user -> new UserShowForm(idx.getAndIncrement(), user.getUserId(), user.getNickname(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserProfileInfo findUserProfileById(Long userId) {
        Optional<User> optionalUser = userRepository.findUserById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
        User user = optionalUser.get();
        return new UserProfileInfo(user.getName(), user.getEmail());
    }

    public ModifyingUserForm findModifyingUserForm(Long userId) {
        Optional<User> optionalUser = userRepository.findUserById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
        User user = optionalUser.get();
        return new ModifyingUserForm(user.getUserId(),
                user.getNickname(),
                user.getPassword(),
                user.getName(),
                user.getEmail());
    }

    public void updateUser(ModifyingUserForm modifyingUserForm) {
        userRepository.updateUser(modifyingUserForm.getUserId(),
                modifyingUserForm.getName(),
                modifyingUserForm.getEmail(),
                modifyingUserForm.getPassword());
    }
}
