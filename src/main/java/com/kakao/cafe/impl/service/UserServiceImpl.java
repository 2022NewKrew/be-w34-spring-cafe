package com.kakao.cafe.impl.service;

import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Override
    public long insertUser(User user) {
        return userRepository.insertUser(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.getAllUser();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userRepository.updateUser(user);
    }
}
