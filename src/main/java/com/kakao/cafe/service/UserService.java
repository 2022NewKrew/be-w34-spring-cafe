package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        System.out.println("서비스");
        System.out.println(user);
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
        if (!findUser.isValidateLogin(user)) {
            return false;
        }
        session.setAttribute("curUser", findUser);
        return true;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
