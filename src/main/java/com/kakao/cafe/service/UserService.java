package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.create(user);
    }

    public UserDTO getUserByUserId(String userId) {
        return new UserDTO(userRepository.findByUserId(userId));
    }

    public List<UserDTO> getUserList() {
        UserListDTO userListDTO = new UserListDTO(userRepository.getUserList());
        return userListDTO.getCopiedUserList();
    }

    public int getUserListSize() {
        return userRepository.getUserList().size();
    }
}
