package com.kakao.cafe.Service;

import com.kakao.cafe.Exception.AuthFailException;
import com.kakao.cafe.Repository.LoginDao;
import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Dto.Login.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginDao loginDao;

    public LoginAuthDto authenticate(LoginRequestDto loginRequestDto) {
        LoginAuthDto loginAuthDto = loginDao.findByEmail(loginRequestDto.getEmail());

        if (loginAuthDto == null) {
            throw new AuthFailException("이메일 또는 비밀번호가 다릅니다.");
        }
        if (!matchPassword(loginRequestDto, loginAuthDto)) {
            throw new AuthFailException("이메일 또는 비밀번호가 다릅니다.");
        }
        return loginAuthDto;
    }

    private boolean matchPassword(LoginRequestDto loginRequestDto, LoginAuthDto loginAuthDto) {
        return loginRequestDto.getPassword().equals(loginAuthDto.getPassword());
    }
}
