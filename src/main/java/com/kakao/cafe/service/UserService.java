package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleLoginForm;
import com.kakao.cafe.dto.SampleUserForm;
import com.kakao.cafe.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> printUsers(){
        return userRepository.findAll();
    }

    public User findUser(Long numID){
        return userRepository.findByNumID(numID);
    }

    public boolean checkPassword(Long numID, String password){
        logger.info("checkPassword for numID : {}", numID);
        User user = userRepository.findByNumID(numID);
        logger.info(user.getPassWord(), password);
        if (user.getPassWord().equals(password)){
            logger.info("Correct password");
            return true;
        }
        logger.info("Invalid password");
        return false;
    }

    public boolean updateUser(SampleUserForm form){
        logger.info("updateUser Input user getID {}", form.getId());
        User user = userRepository.findByUserID(form.getId());
        userRepository.update(user);
        return true;
    }

    public boolean addUser(SampleUserForm form){
        logger.info("addUser with ID : {}", form.getId());
        if (!userRepository.checkExistenceByUserID(form.getId())){
            userRepository.save(form);
            return true;
        }
        logger.info("addUser not executed, already has userID");
        return false;
    }

    public User loginUser(SampleLoginForm form){
        logger.info("loginUser with ID : {}", form.getId());
        return userRepository.checkMatchIDnPW(form.getId(), form.getPassWord());
    }

}
