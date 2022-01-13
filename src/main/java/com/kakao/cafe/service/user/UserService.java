package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.web.dto.user.UserResponseDto;
import com.kakao.cafe.web.dto.user.UsersListResponseDto;
import com.kakao.cafe.web.dto.user.UsersSaveRequestDto;
import org.springframework.stereotype.Service;

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
