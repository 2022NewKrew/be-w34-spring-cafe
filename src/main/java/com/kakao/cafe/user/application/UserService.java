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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void save(UserSaveRequest request) {
        log.info(this.getClass() + ": 회원 가입");
        // TODO: validation
        userRepository.save(request.toUser());
    }

    public void findAll(Map<String, Object> model) {
        log.info(this.getClass() + ": 회원 목록");
        List<User> users = userRepository.findAll();
        List<UserListResponse> userListResponses = users.stream()
                .map(user -> UserListResponse.valueOf(0, user))
                .collect(Collectors.toList());
        model.put("users", userListResponses);
    }

    public void findById(String userId, Map<String, Object> model) {
        log.info(this.getClass() + ": 회원 프로필");
        User user = userRepository.findByIdOrNull(userId);
        UserProfileResponse userProfileResponse = UserProfileResponse.valueOf(user);
        model.put("user", userProfileResponse);
    }
}
