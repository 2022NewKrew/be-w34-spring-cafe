package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDto user) throws SQLException {
        userRepository.save(user.toEntity());
    }

    public UserProfileDto findById(String userId) throws NoSuchElementException {
        User user = userRepository.findByUserId(userId);

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public UserProfileDto findByName(String name) throws NoSuchElementException {
        User user = userRepository.findByName(name);

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public List<UserProfileDto> getUserList() {
        List<UserProfileDto> userDtoList = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            userDtoList.add(new UserProfileDto(user.getUserId(), user.getEmail(), user.getName()));
        }

        return userDtoList;
    }

    public void updateUserProfile(UserProfileDto newProfile, String password) throws NoSuchElementException, IllegalArgumentException {
        User user = userRepository.findByUserId(newProfile.getUserId());

        if (!(password != null && password.equals(user.getPassword()))) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        User newUser = new User(
                user.getId(),
                user.getUserId(),
                newProfile.getEmail(),
                newProfile.getName(),
                user.getPassword()
                );

        userRepository.update(newUser);
    }

    public UserProfileDto checkPassword(String userId, String password) throws NoSuchElementException, IllegalArgumentException {
        User user = userRepository.findByUserId(userId);

        if (!(password != null && password.equals(user.getPassword())))
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");

        return new UserProfileDto(user.getUserId(), user.getEmail(), user.getName());
    }

    public boolean checkSessionUser(String userId, HttpSession session) {
        try {
            return getUserIdFromSession(session).equals(userId);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getUserIdFromSession(HttpSession session) throws NoSuchElementException {
        Object value = session.getAttribute("sessionedUser");

        if (value == null)
            throw new NoSuchElementException(String.format("Invalid session. (%s)", session));

        UserProfileDto user = (UserProfileDto) value;
        return user.getUserId();
    }
}
