package com.kakao.cafe.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(UserRequest userRequest, Model model) {
        System.out.println("hello");
        return "redirect:/user/list";
    }

}
