package com.kakao.cafe.user.application;

import com.kakao.cafe.common.exception.AuthenticationException;
import com.kakao.cafe.common.exception.EntityNotFoundException;
import com.kakao.cafe.user.application.dto.*;
import com.kakao.cafe.user.domain.SessionedUser;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.infra.UserJdbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void save(UserSaveRequest request) {
        log.info(this.getClass() + ": 회원 가입");
        validateUserIdDuplication(request.userId);
        User newUser = request.toUser();

        userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public List<UserListResponse> findAll() {
        log.info(this.getClass() + ": 회원 목록");
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> UserListResponse.valueOf(0, user))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserProfileResponse findById(String userId) {
        log.info(this.getClass() + ": 회원 프로필");
        User user = userRepository.findByIdOrNull(userId);
        this.validateUserExists(user, userId);

        return UserProfileResponse.valueOf(user);
    }

    @Transactional
    public void updateById(String userId, UserUpdateRequest userUpdateRequest, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 개인정보 수정");
        sessionedUser.validateSession(userId);
        validatePassword(userUpdateRequest, sessionedUser);
        User user = userRepository.findByIdOrNull(userId);
        this.validateUserExists(user, userId);

        user.update(userUpdateRequest.password, userUpdateRequest.name, userUpdateRequest.email);
        userRepository.update(user);
    }

    @Transactional(readOnly = true)
    public SessionedUser loginById(UserLoginRequest userLoginRequest) {
        log.info(this.getClass() + ": 회원 로그인");
        User user = userRepository.findByIdOrNull(userLoginRequest.userId);
        validateUserExistsOrThrowAuthFailure(user, userLoginRequest.userId);
        user.verifyPassword(userLoginRequest.password);

        return SessionedUser.valueOf(user);
    }

    public void validateUserExists(String userId) throws EntityNotFoundException {
        User user = userRepository.findByIdOrNull(userId);
        this.validateUserExists(user, userId);
    }

    private void validateUserExists(User user, String userId) throws EntityNotFoundException {
        log.info(this.getClass() + ": 유저 존재 여부 검증");
        if (user == null) {
            EntityNotFoundException.throwNotExistsByField(User.class, "userId", userId);
        }
    }

    private void validateUserExistsOrThrowAuthFailure(User user, String userId) {
        log.info(this.getClass() + ": 유저 존재 여부 검증");
        if (user == null) {
            AuthenticationException.throwAuthFailure(NO_SUCH_USER_EXCEPTION, userId);
        }
    }

    private void validatePassword(UserUpdateRequest request, SessionedUser user) throws AuthenticationException {
        log.info(this.getClass() + ": 개인정보 수정 패스워드 검증");
        if (!user.matchesPassword(request.password)) {
            AuthenticationException.throwAuthFailure(REQUIRED_RE_LOGIN_EXCEPTION);
        }
    }

    private void validateUserIdDuplication(String userId) throws IllegalArgumentException {
        log.info(this.getClass() + ": 유저 아이디 중복 검증");
        if (userRepository.findByIdOrNull(userId) != null) {
            throw new IllegalArgumentException(USER_ID_DUPLICATION_EXCEPTION);
        }
    }
}
