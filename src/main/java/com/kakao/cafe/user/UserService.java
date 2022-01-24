package com.kakao.cafe.user;

import com.kakao.cafe.exception.CustomEmptyDataAccessException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.request.UserRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.exception.CustomDuplicateUserException;
import com.kakao.cafe.user.exception.CustomInvalidedSessionException;
import com.kakao.cafe.user.exception.CustomLoginFailException;
import com.kakao.cafe.user.repository.SessionMapRepository;
import com.kakao.cafe.user.repository.SessionRepository;
import com.kakao.cafe.user.repository.UserH2Repository;
import com.kakao.cafe.user.repository.UserRepository;
import java.util.UUID;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            userRepository.save(userRequest.toUser());
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateUserException(e);
        }
    }

    public UsersResponse findAll() {
        return UsersResponse.toResponse(userRepository.findAll());
    }

    public UserResponse findById(String userId) {
        try {
            return UserResponse.toResponse(userRepository.findById(userId));
        } catch (EmptyResultDataAccessException e) {
            throw new CustomEmptyDataAccessException(e);
        }
    }

    public UUID loginedUserSessionId(UserRequest userRequest) {
        try {
            User user = userRepository.findByIdAndPassword(
                userRequest.getUserId(),
                userRequest.getPassword());
            UUID uuid = UUID.randomUUID();
            sessionRepository.save(uuid, user);
            return uuid;
        } catch (EmptyResultDataAccessException e) {
            throw new CustomLoginFailException();
        }
    }

    public String findUserIdBySessionId(UUID sessionId) {
        User user = sessionRepository.findBySessionId(sessionId)
            .orElseThrow(CustomInvalidedSessionException::new);
        return user.getUserId();
    }

    public void deleteSessionId(UUID sessionId) {
        sessionRepository.delete(sessionId);
    }
}
