package com.kakao.cafe.service;

import com.kakao.cafe.dao.CafeUserDao;
import com.kakao.cafe.dao.CafeUserDaoImpl;
import com.kakao.cafe.helper.UserHelper;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeUserServiceImpl implements CafeUserService {

    private final CafeUserDao cafeUserDao;

    public CafeUserServiceImpl(CafeUserDao cafeUserDao) {
        this.cafeUserDao = cafeUserDao;
    }

    @Override
    public void signUp(User newUser) {
        if(UserHelper.checkRegexOfUser(newUser)){
            cafeUserDao.signUp(newUser);
        }
    }

    @Override
    public List<User> getUserList() {
        return cafeUserDao.getUserList();
    }

    @Override
    public User getUserProfile(String userId) {
        return cafeUserDao.getUserProfile(userId);
    }
}