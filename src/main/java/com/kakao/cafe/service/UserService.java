package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.CannotAuthenticateException;
import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.domain.exception.UnauthorizedException;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.CredentialsDto;
import com.kakao.cafe.service.dto.ModifyUserDto;
import com.kakao.cafe.service.dto.SignUpDto;
import com.kakao.cafe.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // NOTE: thread-safe하지 않아 보임 -> @Transactional을 여기 붙이면 되나?
    public UserDto create(SignUpDto signUp) {
        SignUp entity = signUp.toEntity();
        userRepository.getByUserId(entity.getUserId())
                .ifPresent(user -> { throw new DuplicateUserIdException(); });
        User created = userRepository.create(entity);
        return created.toDto();
    }

    public List<UserDto> list() {
        return userRepository.list()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> get(long id) {
        return userRepository.getById(id).map(User::toDto);
    }

    public UserDto login(CredentialsDto credentials) {
        Optional<User> entity = userRepository.login(
                credentials.getUserId(),
                credentials.getPassword()
        );
        if (entity.isEmpty()) {
            throw new CannotAuthenticateException();
        }
        return entity.get().toDto();
    }

    @Transactional
    public UserDto modify(long actorId, long targetId, ModifyUserDto modifyUser) {
        User target = userRepository.getById(targetId)
                .orElseThrow(NoSuchUserException::new);
        User actor = userRepository.getById(actorId)
                .orElseThrow(NoSuchUserException::new);
        if (!actor.canModify(target, modifyUser.getPassword())) {
            throw new UnauthorizedException();
        }
        modifyPassword(targetId, modifyUser.getPassword());
        modifyName(targetId, modifyUser.getName());
        modifyEmail(targetId, modifyUser.getEmail());
        Optional<User> updated = userRepository.getById(targetId);
        if (updated.isEmpty()) {
            throw new IllegalStateException();
        }
        return updated.get().toDto();
    }

    private void modifyPassword(long id, String password) {
        if (password.equals("")) {
            return;
        }
        userRepository.updatePassword(id, password);
    }

    private void modifyName(long id, String name) {
        userRepository.updateName(id, name);
    }

    private void modifyEmail(long id, String email) {
        userRepository.updateEmail(id, email);
    }
}
