package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.InvalidUsernamePasswordException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.exception.UsernameDuplicatedException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.*;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long addUser(UserCreationForm userCreationForm) {
        User newUser = User.builder()
                           .username(userCreationForm.getUsername())
                           .password(userCreationForm.getPassword())
                           .email(userCreationForm.getEmail())
                           .displayName(userCreationForm.getDisplayName())
                           .build();
        validateDuplicateUserName(newUser);
        return userRepository.add(newUser);
    }

    private void validateDuplicateUserName(User user) {
        if (userRepository.get(user.getUsername()).isPresent()) {
            throw new UsernameDuplicatedException();
        }
    }

    public void updateUser(Long id, UserEditForm userEditForm) {
        User user = User.builder()
                        .id(id)
                        .email(userEditForm.getEmail())
                        .displayName(userEditForm.getDisplayName())
                        .build();
        userRepository.update(user);
    }

    public List<UserView> getAllUserView() {
        return userRepository.getAll().stream().map(
                user -> new UserView(user.getUsername(), user.getEmail(), user.getDisplayName())).collect(
                Collectors.toList());
    }

    public UserView getUserViewByUsername(String username) {
        User user = userRepository.get(username).orElseThrow(UserNotFoundException::new);
        return new UserView(user.getUsername(), user.getEmail(), user.getDisplayName());
    }

    public LoggedInUser login(UserLoginForm userLoginForm) {
        User user = userRepository.get(userLoginForm.getUsername()).orElseThrow(InvalidUsernamePasswordException::new);
        if (user.getPassword().equals(userLoginForm.getPassword())) {
            return new LoggedInUser(user.getId(), user.getUsername());
        }
        throw new InvalidUsernamePasswordException();
    }
}
