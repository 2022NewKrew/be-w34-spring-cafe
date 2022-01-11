package com.kakao.cafe.model.service;

import com.kakao.cafe.model.dto.UserDTO;
import com.kakao.cafe.model.repository.UserRepository;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean registerUser(UserDTO userDTO) {
        if (userRepository.selectUserByUserId(userDTO.getUserId()).isPresent()) {
            throw new UserDuplicatedException("해당 회원 ID를 갖는 회원이 존재합니다.");
        }

        return userRepository.insertUser(userDTO);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.selectAllUsers();
    }

    @Override
    public UserDTO findUserById(Long id) {
        Optional<UserDTO> selectedUser = userRepository.selectUserById(id);

        if (selectedUser.isEmpty()) {
            throw new UserNotFoundException("해당 정보와 일치하는 회원이 존재하지 않습니다.");
        }

        return selectedUser.get();
    }

    @Override
    public UserDTO findUserByUserId(String userId) {
        Optional<UserDTO> selectedUser = userRepository.selectUserByUserId(userId);

        if (selectedUser.isEmpty()) {
            throw new UserNotFoundException("해당 정보와 일치하는 회원이 존재하지 않습니다.");
        }

        return selectedUser.get();
    }

    @Override
    public UserDTO findUserByLoginInfo(String userId, String password) {
        Optional<UserDTO> selectedUser = userRepository.selectUserByLoginInfo(userId, password);

        if (selectedUser.isEmpty()) {
            throw new UserNotFoundException("해당 정보와 일치하는 회원이 존재하지 않습니다.");
        }

        return selectedUser.get();
    }

    @Override
    public boolean modifyUser(UserDTO userDTO) {
        if (userRepository.selectUserByUserId(userDTO.getUserId()).isEmpty()) {
            throw new UserDuplicatedException("해당 회원 ID를 갖는 회원이 존재하지 않습니다.");
        }

        return userRepository.updateUser(userDTO);
    }

    @Override
    public boolean withdrawUser(String userId, String password) {
        if (userRepository.selectUserByLoginInfo(userId, password).isEmpty()) {
            throw new UserDuplicatedException("해당 회원 ID를 갖는 회원이 존재하지 않습니다.");
        }

        return userRepository.deleteUser(userId, password);
    }
}
