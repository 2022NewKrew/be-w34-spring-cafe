package com.kakao.cafe.service;

import com.kakao.cafe.exception.*;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    public void checkDuplicateUser(User duplicateUser) throws DuplicateUserException {
        if(duplicateUser != null)
            throw new DuplicateUserException();
    }

    public void checkExistUser(User user) throws UserNotExistException {
        if(user == null)
            throw new UserNotExistException();
    }

    public void checkLogin(User loginUser) throws NotLoginException {
        if(loginUser == null)
            throw new NotLoginException();
    }

    public void checkSameUser(User user, User loginUser) throws IncorrectUserException {
        if(!user.getUserId().equals(loginUser.getUserId()))
            throw new IncorrectUserException();
    }

    public void checkPassword(String password, String loginPassword) throws IncorrectPasswordException {
        if(!password.equals(loginPassword))
            throw new IncorrectPasswordException();
    }

}
