package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String join(User user) {
        validateNull(user);
        validateDuplicates(user);
        userRepository.saveUser(user);
        return user.getNickName();
    }

    private void validateNull(User user) {
        if (user.getNickName().isBlank() || user.getEmail().isBlank() || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("모든 항목을 입력해야 합니다.");
        }
    }

    private void validateDuplicates(User user) {
        userRepository.findByNickName(user.getNickName())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("중복 닉네임이 존재합니다.");
                });
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("중복 이메일이 존재합니다.");
                });
    }

    public User login(String email, String password) throws IllegalAccessException {
        Optional<User> loginUser = findOneByEmail(email);
        validateLoginEmail(loginUser);
        validateLoginPassword(loginUser, password);
        return loginUser.get();
    }

    private void validateLoginEmail(Optional<User> loginUser) throws IllegalAccessException {
        if (!loginUser.isPresent()) {
            throw new IllegalAccessException("일치하는 가입된 이메일이 존재하지 않습니다.");
        }
    }

    private void validateLoginPassword(Optional<User> loginUser, String password) throws IllegalAccessException {
        if (!loginUser.get().getPassword().equals(password)) {
            throw new IllegalAccessException("비밀번호가 일치하지 않습니다.");
        }
    }

    public User getLoggedInUser(Object sessionedUser) throws IllegalAccessException {
        if (sessionedUser != null) {
            User user = (User) sessionedUser;
            Optional<User> sessionUser = userRepository.findByUserId(user.getId());
            return sessionUser.get();
        }
        throw new IllegalAccessException("로그인 후에 이용하세요.");
    }

    public void editUserInfo(Long userId, String newEmail, String newNickName) {
        userRepository.editUserInfo(userId, newEmail, newNickName);
    }

    public List<User> findUsers() {
        return userRepository.findAllUsers();
    }

    public Optional<User> findOneByNickname(String userNickname) {
        return userRepository.findByNickName(userNickname);
    }

    public Optional<User> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findOneById(Long id) {
        return userRepository.findByUserId(id);
    }

}
