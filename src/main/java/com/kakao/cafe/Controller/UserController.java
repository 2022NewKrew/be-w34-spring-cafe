package com.kakao.cafe.Controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.model.UserModel;
import com.kakao.cafe.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/form")
    public String form() {
        return "user/form";
    }

    @GetMapping("/users/form/{userId}")
    public String updateFrom(@PathVariable String userId, Model model) {
        User user = userRepository.findById(userId);
        model.addAttribute("user", UserModel.fromUser(user));
        return "user/updateForm";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserModel userModel) {
        userRepository.insert(new User(userModel));
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String user(@PathVariable String userId, Model model) {
        User target = userRepository.findById(userId);
        if (target != null)
            model.addAttribute("user", UserModel.fromUser(target));
        return "user/userProfile";
    }

    @PostMapping("users/{userId}")
    public String updateUser(@ModelAttribute UserModel userModel, String oldPassword) {
        User newInfo = new User(userModel);
        User oldUser = userRepository.findById(userModel.getUserId());
        if (oldUser.canModify(oldPassword.trim()))
            userRepository.update(newInfo);
        return "redirect:/users";
    }


    @GetMapping("/users")
    public String users(Model model) {
        Users users = userRepository.findAll();
        List<UserModel> userModels = users.getUsers().stream()
                .map(UserModel::fromUser)
                .collect(Collectors.toList());
        model.addAttribute("users", userModels);
        return "user/list";
    }

    @GetMapping("/users/deleteAll")
    public String deleteAll() {
        userRepository.deleteAll();
        return "redirect:/posts";
    }


}
