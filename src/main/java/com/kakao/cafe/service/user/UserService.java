package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.dto.user.UserUpdateRequestDto;
import com.kakao.cafe.dto.user.UsersSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository users = new UserRepository();

    public void add(UsersSaveRequestDto reqDto){
        users.addUser(reqDto);
    }

    public List<User> findAll(){
        return users.findAll();
    }

    public UserResponseDto findById(int id){
        return users.findUserById(id);
    }

    public void update(int id, UserUpdateRequestDto userDto) {
        users.update(id, userDto);
    }
}
