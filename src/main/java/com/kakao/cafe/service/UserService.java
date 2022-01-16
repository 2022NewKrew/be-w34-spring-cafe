package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UsersDTO;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getName());
        user.setUserPassword(userDTO.getPassWord());
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);
    }

    public UsersDTO findAll() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return null;
        }

        return new UsersDTO(users);
    }

    public UserDTO findById(String userId) {
        User user = userRepository.findById(userId);

        return new UserDTO(user);
    }
}

