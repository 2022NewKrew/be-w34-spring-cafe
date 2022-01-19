package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.IllegalUserInputException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserListDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO userDTO) {

        Optional<User> user = userRepository.findByUserId(userDTO.getUserId());
        if (user.isEmpty())
            userRepository.create(new User(userDTO));
        else
            throw new IllegalUserInputException("이미 존재하는 아이디입니다.");
    }

    public UserDTO getUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent())
            return UserDTO.newInstance(user.get());
        throw new IllegalUserInputException("해당 아이디의 유저가 없습니다.");
    }

    public List<UserDTO> getUserList() {
        UserListDTO userListDTO = new UserListDTO(userRepository.getUserList());
        return userListDTO.getCopiedUserList();
    }

    public int getUserListSize() {
        return userRepository.getUserList().size();
    }
}
