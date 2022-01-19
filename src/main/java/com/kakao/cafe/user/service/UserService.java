package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.exception.*;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.util.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void joinUser(User user) {
        validateDuplicateUser(user.getUserId());
        userRepository.save(user);
    }

    private void validateDuplicateUser(UserId userId) {
        boolean isExist = userRepository.findByUserId(userId).isPresent();
        if (isExist) {
            throw new DuplicateUserException(ErrorCode.DUPLICATE_USER_ID);
        }
    }

    public User login(UserId userId, Password password) {
        Optional<User> user = userRepository.findByUserIdAndPassword(userId, password);
        return findExistUser(user, new LoginFailedException(ErrorCode.FAILED_LOGIN));
    }

    public void updateUser(User updateUser, UserId originalUserId) {
        boolean isChangedUserId = !updateUser.equalId(originalUserId);
        if (isChangedUserId) {
            throw new UpdateUserException(ErrorCode.CANNOT_CHANGE_USER_ID);
        }

        Optional<User> user = userRepository.findByUserId(updateUser.getUserId());
        User originalUser = findExistUser(user, new UpdateUserException(ErrorCode.FAILED_LOGIN));
        boolean isCorrectPassword = updateUser.equalPassword(originalUser);
        if (!isCorrectPassword) {
            throw new UpdateUserException(ErrorCode.WRONG_USER_PASSWORD);
        }
        userRepository.update(updateUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLoginUserId(UserId userId, UserId loginId) {
        boolean isLoginUserId = loginId.equals(userId);
        if (!isLoginUserId) {
            throw new ForbiddenUserException(ErrorCode.FORBIDDEN_USER);
        }
        return findByUserId(userId);
    }

    public User findByUserId(UserId userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return findExistUser(user, new NonExistUserException(ErrorCode.USER_NOT_FOUND));
    }

    private User findExistUser(Optional<User> user, RuntimeException exception) {
        if (user.isEmpty()) {
            throw exception;
        }
        return user.get();
    }
}