package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.dto.UserSaveDto;
import org.springframework.stereotype.Service;

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

    public User findbyId(int id){
        return users.findById(id);
    }
}
