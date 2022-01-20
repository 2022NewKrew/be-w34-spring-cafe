package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.request.UserRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.repository.SessionMapRepository;
import com.kakao.cafe.user.repository.SessionRepository;
import com.kakao.cafe.user.repository.UserH2Repository;
import com.kakao.cafe.user.repository.UserRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public UserService(UserH2Repository userRepository, SessionMapRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void save(UserRequest userRequest) {
        userRepository.save(userRequest.toUser());
    }

    public UsersResponse findAll() {
        return UsersResponse.toResponse(userRepository.findAll());
    }

    public UserResponse findById(String userId) {
        return UserResponse.toResponse(userRepository.findById(userId));
    }

    public UUID loginedUserSessionId(UserRequest userRequest) {
        User user = userRepository.findByIdAndPassword(
            userRequest.getUserId(),
            userRequest.getPassword());
        UUID uuid = UUID.randomUUID();
        sessionRepository.save(uuid, user);
        return uuid;
    }

    public String findUserIdBySessionId(UUID sessionId) {
        User user = sessionRepository.findBySessionId(sessionId);
        return user.getUserId();
    }

    public void deleteSessionId(UUID sessionId) {
        sessionRepository.delete(sessionId);
    }
}
