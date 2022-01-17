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
    public static final String INCORRECT_PASSWORD_ERROR = "/error/incorrect_password_error";
    public static final String NOT_LOGIN_ERROR = "/error/not_login_error";
    public static final String USER_NOT_EXIST_ERROR = "/error/user_not_exist_error";
    public static final String INCORRECT_USER_ERROR = "/error/incorrect_user_error";

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

    public String updateUser(User user, HttpSession session) {
        User loginUser = getLoginUser(session);
        if(loginUser == null)
            return NOT_LOGIN_ERROR;
        if(!isSameUser(user, loginUser))
            return INCORRECT_USER_ERROR;
        if(!checkPassword(user.getPassword(), loginUser.getPassword()))
            return INCORRECT_PASSWORD_ERROR;
        userDao.updateUser(user);
        return "redirect:/users";
    }

    public String getLoginProfile(User loginUser, Model model) {
        if(loginUser == null)
            return NOT_LOGIN_ERROR;
        model.addAttribute("user", loginUser);
        return "/user/profile";
    }

    public String login(String userId, String password, HttpSession session) {
        User user = userDao.getUser(userId);
        if(user == null)
            return USER_NOT_EXIST_ERROR;
        if(!checkPassword(password, user.getPassword()))
            return INCORRECT_PASSWORD_ERROR;
        session.setAttribute(SESSIONED_USER, user);
        return "redirect:/";
    }

    public boolean isSameUser(User user, User loginUser) {
        if(user.getUserId().equals(loginUser.getUserId()))
            return true;
        return false;
    }

    public boolean checkPassword(String password, String loginPassword) {
        if(password.equals(loginPassword))
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
