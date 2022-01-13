package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dao.UserSignUpDao;
import com.kakao.cafe.domain.user.dto.UserSignUpDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {

    private static final Logger logger = LoggerFactory.getLogger(UserSignUpService.class);

    private final UserSignUpDao userSignUpDao;

    public UserSignUpService(UserSignUpDao userSignUpDao) {
        this.userSignUpDao = userSignUpDao;
    }

    public void signUp(UserSignUpDto dto) {
        userSignUpDao.signUp(dto);
    }
}
