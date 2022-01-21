package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dao.UserDao;
import com.kakao.cafe.domain.user.dto.UserLoginDto;
import com.kakao.cafe.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDao userDao;

    public User login(UserLoginDto dto) {
        User user = userDao.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(dto.getEmail()));
        user.validatePassword(dto.getPassword());
        return user;
    }
}
