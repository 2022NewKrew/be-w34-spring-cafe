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

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthInfo login(Login loginDTO) {
        Optional<User> foundUser = userRepository.findUserByUid(loginDTO.getUid());
        if (foundUser.isEmpty()) {
            throw new AuthInvalidUidException(ErrorCode.NOT_FOUND, loginDTO.getUid());
        }
        if (!foundUser.get().validPassword(loginDTO.getPassword())) {
            throw new AuthInvalidPasswordException(ErrorCode.INVALID_VALUE, loginDTO.getUid());
        }

        return AuthInfo.of(foundUser.get().getUid());
    }
}
