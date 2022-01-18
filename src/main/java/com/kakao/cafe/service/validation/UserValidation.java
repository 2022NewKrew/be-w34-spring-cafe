package com.kakao.cafe.service.validation;

import com.kakao.cafe.exception.user.DuplicateUserIdException;
import com.kakao.cafe.exception.user.IncorrectPasswordException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    public void validateUser(UserVo user) {
        if (user == null) {
            throw new UserNotFoundException();
        }
    }

    public void validateSignup(UserVo user) {
        if (user != null) {
            throw new DuplicateUserIdException();
        }
    }

    public void validateLogin(UserVo user, String password) {
        validateUser(user);
        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException();
        }
    }

    public void validateUpdate(String password, String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IncorrectPasswordException();
        }
    }
}
