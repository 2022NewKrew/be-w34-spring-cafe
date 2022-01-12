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

    public User findUser(String userId) {
        return userRepository.findByUserId(userId).
                orElseThrow(() -> new IllegalArgumentException("해당 userId에 맞는 user가 존재하지 않습니다."));
    }
    public User findUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 user가 존재하지 않습니다."));
    }
    public List<User> findUserList() {
        return userRepository.findAll();
    }

    public void updateUserInfo(Long id, User updateUser) {
        User user = findUser(id);
        validatePasswordMember(user.getPassword(), updateUser.getPassword());
        userRepository.updateUser(id, updateUser);
    }

    private void validatePasswordMember(String userPassword, String updateUserPassword) {
        if(!userPassword.equals(updateUserPassword)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
