package com.kakao.cafe.service.user;

import com.kakao.cafe.repository.Users;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.dto.user.UserUpdateRequestDto;
import com.kakao.cafe.dto.user.UsersListResponseDto;
import com.kakao.cafe.dto.user.UsersSaveRequestDto;
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

    public void update(int id, UserUpdateRequestDto userDto) {
        users.update(id, userDto);
    }
}
