package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.servlet.http.HttpSession;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    public User findById(String userId) {
        return userRepository.findById(userId);
    }

    public boolean isvalidateLogin(User user, HttpSession session) {
        User findUser = userRepository.findById(user.getUserId());
        if(!findUser.isValidateLogin(user)){
            return false;
        }
        session.setAttribute("curUser", findUser);
        return true;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
