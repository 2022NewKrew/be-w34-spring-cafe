package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
import com.kakao.cafe.user.dto.UserListDTO;
import com.kakao.cafe.user.dto.UserLoginDTO;
import com.kakao.cafe.user.dto.UserProfileDTO;
import com.kakao.cafe.user.repository.UserJdbcRepository;
import com.kakao.cafe.user.repository.UserMemoryRepository;
import com.kakao.cafe.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void userCreate(UserCreateDTO userCreateDTO){
        User user = new User(userCreateDTO);
        userRepository.addUser(user);
    }

    public void userUpdate(UserCreateDTO userCreateDTO){
        User user = userRepository.getUserByCondition("userid", userCreateDTO.getUserId());

        if(user == null){
            throw new RuntimeException("유저 아이디에 해당하는 유저가 없습니다.");
        }

        //비밀번호가 일치하지 않는경우 return
        if(!user.getPassword().equals(userCreateDTO.getPassword())){
            return;
        }

        User updatedUser = new User(userCreateDTO);
        userRepository.updateUser(updatedUser);
    }

    public Boolean isValidLogin(UserLoginDTO userLoginDTO){
        User user = userRepository.getUserByCondition("userid", userLoginDTO.getUserId());

        if(user == null){
            logger.error("존재하는 사용자 아이디가 없습니다. (" + (userLoginDTO.getUserId()) + ")");
            return false;
        }

        //비밀번호가 일치하지 않는경우
        if(!user.getPassword().equals(userLoginDTO.getPassword())){
            return false;
        }

        return true; //로그인 가능한경우 true
    }

    public List<User> getAllUser(){
        return userRepository.getUsers();
    }

    public User getUserByUserId(String userId){
        User user = userRepository.getUserByCondition("userid", userId);

        if(user == null){
            throw new RuntimeException("유저 아이디에 해당하는 유저가 없습니다.");
        }

        return user;
    }
}
