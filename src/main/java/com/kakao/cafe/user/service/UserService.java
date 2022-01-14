package com.kakao.cafe.user.service;

import com.kakao.cafe.user.UserMapper;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.dto.UserView;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registerUser(UserCreationForm userCreationForm) {
        User user = UserMapper.toUser(userCreationForm);
        validateDuplicateUserName(user);

        return userRepository.add(user);
    }

    private void validateDuplicateUserName(User user) {
        if (userRepository.get(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use: " + user.getUsername());
        }
    }

    public List<UserView> getAllUserView() {
        return userRepository.getAll().stream().map(UserMapper::toUserView).collect(Collectors.toList());
    }

    public UserView getUserViewByUsername(String username) {
        return UserMapper.toUserView(userRepository.get(username).orElseThrow(
                () -> new NoSuchElementException("Username not found: " + username)));
    }
}
