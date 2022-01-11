package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.web.dto.UsersSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private Users users = new Users();

    public void add(UsersSaveRequestDto reqDto){
        users.addUser(reqDto);
    }

    public List<User> findAll(){
        return users.getUsers();
    }

    public User findById(int id){
        return users.findUserById(id);
    }
}
