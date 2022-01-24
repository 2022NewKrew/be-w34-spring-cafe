package com.kakao.cafe.web;

import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleUserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

public class Users {
    private final static Logger logger = LoggerFactory.getLogger(Users.class);
    private ArrayList<User> userList;

    public Users() {
        this.userList = new ArrayList<>();
    }

    public boolean addUser(SampleUserForm form){
        logger.info("addUser Input user ID : {}", form.getId());
        if (!checkUser(form.getId())){
            userList.add(User.add(form));
            return true;
        }
        return false;
    }

    public User findUser(String userID){
        logger.info("findUser Input user ID : {}", userID);
        Optional<User> opt = userList.stream().filter(user -> user.getId().equals(userID)).findFirst();
        if (opt.isPresent()){
            return opt.get();
        }
        logger.error("Can not find the User with userID");
        throw new RuntimeException("Can not find the user with userID");
    }

    public boolean updateUser(SampleUserForm form){
        logger.info("updateUser Input user getID {}", form.getId());
        User findUser = findUser(form.getId());
        if (findUser.getPassWord().equals(form.getPassWord())){
            findUser.update(form);
            return true;
        }
        logger.info("updateUser update not executed, Invalid password");
        return false;
    }

    public boolean checkUser(String userID){
        logger.info("Check the User Id existence userID : {}", userID);
        Optional<User> opt = userList.stream().filter(user -> user.getId().equals(userID)).findFirst();
        if (opt.isPresent()){
            return true;
        }
        return false;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
