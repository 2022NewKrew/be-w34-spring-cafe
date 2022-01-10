package com.kakao.cafe.Controller;

import com.kakao.cafe.domain.user.*;
import com.kakao.cafe.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private Users users = new Users();

    @GetMapping("/")
    public String index() {
        return "posts";
    }

    @GetMapping("/users/form")
    public String form() {
        return "user/form";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserModel userModel) {
        users.add(new User(userModel));
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String user(@PathVariable String userId, Model model) {
        User target = users.getByUserId(userId);
        if(target != null)
            model.addAttribute("user", UserModel.fromUser(target));
        return "user/userProfile";
    }


    @GetMapping("/users")
    public String users(Model model) {
        List<UserModel> userModels = users.getUsers().stream()
                .map(u -> UserModel.fromUser(u))
                .collect(Collectors.toList());
        model.addAttribute("users", userModels);
        return "user/list";
    }

    public Users getUsers(){
        return users;
    }

}
