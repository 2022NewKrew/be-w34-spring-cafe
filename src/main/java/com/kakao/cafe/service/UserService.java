package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.dto.UserSaveDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    //DB생성 전까지 users 변수에 임시 저장
    private Users users = new Users();

    public void save(UserSaveDto userSaveDto){
        users.addUser(userSaveDto);
    }

    public List<User> findAll(){
        return users.getUsers();
    }
}
