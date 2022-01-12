package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Session;
import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.CredentialsDto;
import com.kakao.cafe.service.dto.SignUpDto;
import com.kakao.cafe.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Nullable
    public UserDto create(SignUpDto signUp, String sessionId) {
        SignUp entity = signUp.toEntity();
        User created = repository.create(entity);
        if (created == null) {
            return null;
        }
        repository.saveSession(new Session(sessionId, created.getId()));
        return created.toDto();
    }

    public List<UserDto> list() {
        return repository.list()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @Nullable
    public UserDto getById(String id, String sessionId) {
        Long userId = parseId(id, sessionId);
        if (userId == null) {
            return null;
        }
        User found = repository.getById(userId);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }

    @Nullable
    public UserDto login(CredentialsDto credentials, String sessionId) {
        User found = repository.login(
                credentials.getUserId(),
                credentials.getPassword()
        );
        if (found == null) {
            return null;
        }
        repository.saveSession(new Session(sessionId, found.getId()));
        return found.toDto();
    }

    @Nullable
    private Long parseId(String id, String sessionId) {
        if (id.equals("me")) {
            return getIdFromSession(sessionId);
        }
        return Long.parseLong(id);
    }

    @Nullable
    private Long getIdFromSession(String sessionId) {
        Session session = repository.getSession(sessionId);
        if (session == null) {
            return null;
        }
        return session.getUserId();
    }
}
