package com.kakao.cafe.application.user;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;

import java.util.Optional;

public class SignUpUserService {

    private final SignUpUserPort signUpUserPort;
    private final FindUserPort findUserPort;

    public SignUpUserService(SignUpUserPort signUpUserPort, FindUserPort findUserPort) {
        this.signUpUserPort = signUpUserPort;
        this.findUserPort = findUserPort;
    }

    public void join(UserVo userVo) throws IllegalArgumentException {
        Optional<User> optionalUser = findUserPort.findByUserId(userVo.getUserId());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 ID는 가입할 수 없습니다.");
        }

        signUpUserPort.save(userVo.convertVoToEntity());
    }
}
