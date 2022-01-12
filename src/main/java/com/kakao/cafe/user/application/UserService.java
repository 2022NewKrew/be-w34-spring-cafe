package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.dto.UserListResponse;
import com.kakao.cafe.user.dto.UserProfileResponse;
import com.kakao.cafe.user.dto.UserSaveRequest;
import com.kakao.cafe.user.infra.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakao.cafe.common.ErrorMessage.USER_ID_DUPLICATION_EXCEPTION;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void save(UserSaveRequest request) {
        log.info(this.getClass() + ": 회원 가입");
        try {
            validateUserIdDuplication(request.userId);
            User newUser = request.toUser();
            userRepository.save(newUser);
        } catch (Exception e) {
            log.info(e.toString());
        }
    }

    public void validateUserIdDuplication(String userId) throws IllegalArgumentException {
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
        return UserProfileResponse.valueOf(user);
    }
}
