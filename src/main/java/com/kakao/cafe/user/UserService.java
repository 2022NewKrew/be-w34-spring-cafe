package com.kakao.cafe.user;

import com.kakao.cafe.exception.NoSuchIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:58
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
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

    @Transactional
    public User updateUser(UserDto userDto, int id) {
        User expectedUser = findUserById(id);
        if (!passwordEncoder.matches(userDto.getPassword(), expectedUser.getPassword())) {
            return null;
        }
        User updatedUser = userDto.toEntity();
        updatedUser.setId(id);
        return userRepository.update(updatedUser);
    }
}
