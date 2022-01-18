package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.validation.DuplicatedUserIdException;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;
import com.kakao.cafe.domain.user.UserVo;

import java.util.Optional;

public class SignUpUserService {
    private final UserDaoPort userDao;

    public SignUpUserService(UserDaoPort userDao) {
        this.userDao = userDao;
    }


    public void join(UserVo userVo) throws DuplicatedUserIdException {
        Optional<User> optionalUser = userDao.findByUserId(userVo.getUserId());
        if (optionalUser.isPresent()) {
            throw new DuplicatedUserIdException();
        }

        userDao.save(userVo.convertVoToEntity());
    }
}
