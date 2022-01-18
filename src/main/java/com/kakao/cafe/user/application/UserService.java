package com.kakao.cafe.user.application;

import com.kakao.cafe.common.exception.AuthenticationException;
import com.kakao.cafe.common.exception.EntityNotFoundException;
import com.kakao.cafe.user.domain.SessionedUser;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.dto.*;
import com.kakao.cafe.user.infra.UserJdbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakao.cafe.common.exception.ExceptionMessage.*;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserJdbcRepository userJdbcRepository) {
        this.userRepository = userJdbcRepository;
    }

    public void save(UserSaveRequest request) {
        log.info(this.getClass() + ": 회원 가입");
        validateUserIdDuplication(request.userId);
        User newUser = request.toUser();
        userRepository.save(newUser);
    }

    private void validateUserIdDuplication(String userId) throws IllegalArgumentException {
        if (userRepository.existsById(userId)) {
            throw new IllegalArgumentException(USER_ID_DUPLICATION_EXCEPTION);
        }
    }

    public List<UserListResponse> findAll() {
        log.info(this.getClass() + ": 회원 목록");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserListResponse.valueOf(0, user))
                .collect(Collectors.toList());
    }

    public UserProfileResponse findById(String userId) {
        log.info(this.getClass() + ": 회원 프로필");
        User user = userRepository.findByIdOrNull(userId);
        if (user == null) {
            EntityNotFoundException.throwNotExistsByField(User.class, "userId", userId);
        }
        return UserProfileResponse.valueOf(user);
    }

    public void updateById(String userId, UserUpdateRequest userUpdateRequest, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 개인정보 수정");
        validateUpdateRequest(userId, userUpdateRequest, sessionedUser);
        User user = userRepository.findByIdOrNull(userId);
        if (user == null) {
            EntityNotFoundException.throwNotExistsByField(User.class, "userId", userId);
        }

        user.update(userUpdateRequest.password, userUpdateRequest.name, userUpdateRequest.email);
        userRepository.update(user);
    }

    public SessionedUser loginById(UserLoginRequest userLoginRequest) {
        log.info(this.getClass() + ": 회원 로그인");
        User user = userRepository.findByIdOrNull(userLoginRequest.userId);
        if (user == null) {
            AuthenticationException.throwAuthFailure(NO_SUCH_USER_EXCEPTION, userLoginRequest.userId);
        }
        user.validatePassword(userLoginRequest.password);

        return SessionedUser.valueOf(user);
    }

    private void validateUpdateRequest(String userId, UserUpdateRequest request, SessionedUser user)
            throws AuthenticationException {
        if (!(user.hasSameUserLoggedIn(userId) && user.matchesPassword(request.password))) {
            AuthenticationException.throwAuthFailure(REQUIRED_RE_LOGIN_EXCEPTION);
        }
    }
}
