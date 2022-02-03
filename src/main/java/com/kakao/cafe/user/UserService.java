package com.kakao.cafe.user;

import com.kakao.cafe.exception.NoSuchIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDto.toEntity();
        userRepository.insert(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {
        try {
            return userRepository.findUserById(id);
        } catch (DataAccessException e) {
            throw new NoSuchIdException();
        }
    }

    public User updateUser(UserDto userDto, int id) {
        User user = userDto.toEntity();
        user.setId(id);
        return userRepository.update(user);
    }
}
