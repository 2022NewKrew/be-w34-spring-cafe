package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Override
    public long insertUser(UserDTO user) {
        return userRepository.insertUser(user);
    }

    @Override
    public List<UserDTO> getUserList() {
        return userRepository.getAllUser();
    }

    @Override
    public UserDTO getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public int updateUser(UserDTO user) {
        return userRepository.updateUser(user);
    }

    @Override
    public UserDTO getUserByLoginData(LoginDTO login) {
        return userRepository.getUserByLoginData(login);
    }
}
