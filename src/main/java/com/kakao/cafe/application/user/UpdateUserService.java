package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.validation.NonExistsUserIdException;
import com.kakao.cafe.domain.user.UserDaoPort;
import com.kakao.cafe.domain.user.UserVo;

public class UpdateUserService {
    private final UserDaoPort userDao;

    public UpdateUserService(UserDaoPort userDao) {
        this.userDao = userDao;
    }

    public void updateInformation(UserVo updateUserVo) throws NonExistsUserIdException {
        userDao.findByUserId(updateUserVo.getUserId())
                .orElseThrow(() -> new NonExistsUserIdException());

        userDao.update(updateUserVo.convertVoToEntity());
    }
}
