package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUser(String userId) {
        return userRepository.findByUserId(userId);
    }
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }
    public List<User> findUserList() {
        return userRepository.findAll();
    }

    public void updateUserInfo(Long id, User updateUser) {
        Optional<User> user = userRepository.findById(id);
        validatePasswordMember(user.get().getPassword(), updateUser.getPassword());
        userRepository.updateUser(id, updateUser);
    }

    private void validatePasswordMember(String userPassword, String updateUserPassword) {
        if(!userPassword.equals(updateUserPassword)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
