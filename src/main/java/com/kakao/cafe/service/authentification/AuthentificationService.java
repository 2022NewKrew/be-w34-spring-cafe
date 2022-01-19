package com.kakao.cafe.service.authentification;

import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.common.exception.custom.LoginFailedException;
import com.kakao.cafe.common.exception.custom.UserNotFoundException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final UserRepository userRepository;

    public UserIdentification login(String userId, String password) {
        User loginUser = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        if(!loginUser.isCorrectPassword(password)) {
            throw new LoginFailedException(ErrorCode.PASSWORD_INCORRECT);
        }
        return UserIdentification.of(loginUser.getUserId(), loginUser.getUserName(), loginUser.getEmail());
    }
}
