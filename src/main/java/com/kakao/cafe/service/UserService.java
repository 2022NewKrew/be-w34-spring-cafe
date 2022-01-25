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
        Optional<User> user = userRepository.findByNumID(numID);
        if (user.isPresent()){
            return user.get();
        }
        throw new RuntimeException("일치하는 userID가 없습니다.");
    }

    public boolean updateUser(SampleUserForm form){
        logger.info("updateUser Input user getID {}", form.getId());

        Optional<User> user = userRepository.findByUserID(form.getId());
        if (!user.isPresent()){
            throw new RuntimeException("Update할 user가 없습니다.");
        }

        if (user.get().getPassWord().equals(form.getPassWord())){
            user.get().update(form);
            userRepository.update(user.get());
            return true;
        }
        logger.info("UpdateUser update not executed, Invalid password");
        return false;
    }

    public boolean addUser(SampleUserForm form){
        logger.info("addUser with ID : {}", form.getId());
        Optional<User> user = userRepository.findByUserID(form.getId());
        if (!user.isPresent()){
            userRepository.save(form);
            return true;
        }
        logger.info("addUser not executed, already has userID");
        return false;
    }

    public Optional<User> loginUser(SampleLoginForm form){
        logger.info("loginUser with ID : {}", form.getId());
        Optional<User> user = userRepository.findByUserID(form.getId());
        if (!user.isPresent()){
            throw new RuntimeException("Login할 user가 없습니다.");
        }

        if (user.get().getPassWord().equals(form.getPassWord())){
            // login 성공
            return user;
        }
        // login 실패
        return Optional.ofNullable(null);
    }

}
