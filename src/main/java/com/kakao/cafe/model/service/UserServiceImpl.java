package com.kakao.cafe.model.service;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.repository.UserRepository;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(User user) {
        if (userRepository.selectUserByID(user.getId()).isPresent()) {
            throw new UserDuplicatedException("해당 회원 ID를 갖는 회원이 존재합니다.");
        }

        return userRepository.insertUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.selectAllUsers();
    }

    @Override
    public User findUserByID(String id) {
        Optional<User> selectedUser = userRepository.selectUserByID(id);

        if (selectedUser.isEmpty()) {
            throw new UserNotFoundException("해당 정보와 일치하는 회원이 존재하지 않습니다.");
        }

        return selectedUser.get();
    }

    @Override
    public User findUserByLoginInfo(String id, String password) {
        Optional<User> selectedUser = userRepository.selectUserByLoginInfo(id, password);

        if (selectedUser.isEmpty()) {
            throw new UserNotFoundException("해당 정보와 일치하는 회원이 존재하지 않습니다.");
        }

        return selectedUser.get();
    }

    @Override
    public boolean modifyUser(User user) {
        if (userRepository.selectUserByID(user.getId()).isEmpty()) {
            throw new UserDuplicatedException("해당 회원 ID를 갖는 회원이 존재하지 않습니다.");
        }

        return userRepository.updateUser(user);
    }

    @Override
    public boolean withdrawUser(String id, String password) {
        if (userRepository.selectUserByLoginInfo(id, password).isEmpty()) {
            throw new UserDuplicatedException("해당 회원 ID를 갖는 회원이 존재하지 않습니다.");
        }

        return userRepository.deleteUser(id, password);
    }
}
