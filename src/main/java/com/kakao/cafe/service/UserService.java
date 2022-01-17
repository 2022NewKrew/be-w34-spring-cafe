package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    public static final String SESSIONED_USER = "sessionedUser";

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public String updateUser(User user, User loginUser) {
        if(loginUser == null)
            return "/error/not_login_error";
        if(!isSameUser(user, loginUser))
            return "/error/incorrect_user_error";
        userDao.updateUser(user);
        return "redirect:/users";
    }

    public String getLoginProfile(User loginUser, Model model) {
        if(loginUser == null)
            return "/error/not_login_error";
        model.addAttribute("user", loginUser);
        return "/user/profile";
    }

    public void login(String userId, String password, HttpSession session) {
        User user = userDao.getUser(userId, password);
        session.setAttribute(SESSIONED_USER, user);
    }

    public boolean isSameUser(User user, User loginUser) {
        if(user.getUserId().equals(loginUser.getUserId()))
            return true;
        return false;
    }

    public User getLoginUser(HttpSession session) {
        Object loginUserObject = session.getAttribute(SESSIONED_USER);
        if(loginUserObject == null) {
            return null;
        }
        return (User)loginUserObject;
    }

}
