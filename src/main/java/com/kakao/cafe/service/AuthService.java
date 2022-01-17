package com.kakao.cafe.service;

import com.kakao.cafe.dto.AuthInfoDTO.Login;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.AuthInvalidUidException;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public AuthInfo login(Login loginDTO) {
        Optional<User> foundUser = userRepository.findUserByUid(loginDTO.getUid());
        if (foundUser.isEmpty()) {
            throw new AuthInvalidUidException(ErrorCode.NOT_FOUND, loginDTO.getUid());
        }
        if (!foundUser.get().matchPassword(loginDTO.getPassword())) {
            throw new AuthInvalidPasswordException(ErrorCode.AUTHENTICATION_INVALID,
                loginDTO.getUid());
        }

        return AuthInfo.of(foundUser.get().getUid());
    }
}
