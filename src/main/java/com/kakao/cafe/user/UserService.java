package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:58
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDto userDto) {
        User user = new User(
                null,
                userDto.getName(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getEmail());
        userRepository.insert(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User updateUser(UserDto userDto, int id) {
        User user = new User(id, null, null, userDto.getName(), userDto.getEmail());
        return userRepository.update(user);
    }
}
