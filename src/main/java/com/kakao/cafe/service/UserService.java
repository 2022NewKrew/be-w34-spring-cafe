package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.web.dto.UserResponseDto;
import com.kakao.cafe.web.dto.UsersListResponseDto;
import com.kakao.cafe.web.dto.UsersSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private Users users = new Users();

    public void add(UsersSaveRequestDto reqDto){
        users.addUser(reqDto);
    }

    public UsersListResponseDto findAll(){
        return users.findAll();
    }

    public UserResponseDto findById(int id){
        return users.findUserById(id);
    }
}
