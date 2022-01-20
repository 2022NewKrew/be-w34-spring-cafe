package com.kakao.cafe.service;

import com.kakao.cafe.dao.CafeUserDao;
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
    public boolean signUp(User newUser) {
        if(UserHelper.checkRegexOfUser(newUser)){
            return cafeUserDao.signUp(newUser);
        }
        return false;
    }

    @Override
    public boolean SignIn(User signInUser) {
        if(cafeUserDao.SignIn(signInUser)) {
            signInUser.setPassword(null);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUserList() {
        return cafeUserDao.getUserList();
    }

    @Override
    public User getUserProfile(String userId) {
        return cafeUserDao.getUserProfile(userId);
    }

    @Override
    public boolean adminEditProfile(String loginUser, String inputPassword) {
        if (loginUser != null && UserHelper.checkRegexOfPassword(inputPassword)) {
            return cafeUserDao.adminEditProfile(loginUser,inputPassword);
        }
        return false;
    }

    @Override
    public boolean editProfile(String loginUser, User updateUser) {
        if(loginUser != null && UserHelper.checkRegexOfUpdate(updateUser)) {
            return cafeUserDao.editProfile(loginUser, updateUser);
        }
        return false;
    }

    @Override
    public boolean deleteProfile(String userId) {
        return cafeUserDao.deleteProfile(userId);
    }
}