package com.kakao.cafe.service.validation;

import com.kakao.cafe.model.vo.UserVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserValidation {

    public void validateUser(UserVo user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디 없음");
        }
    }

    public void validateSignup(UserVo user) {
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디 이미 존재");
        }
    }

    public void validateLogin(UserVo user, String password) {
        validateUser(user);
        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 틀림");
        }
    }

    public void validateUpdate(UserVo user, String newPassword) {
        String oldPassword = user.getPassword();
        if (!oldPassword.equals(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "기존 비밀번호 불일치");
        }
    }
}
