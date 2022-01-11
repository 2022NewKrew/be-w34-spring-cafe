package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.ModifyingUserForm;
import com.kakao.cafe.dto.user.UserProfileInfo;
import com.kakao.cafe.dto.user.UserRegistrationForm;
import com.kakao.cafe.dto.user.UserShowForm;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String registerUser(@RequestParam("userId") String nickName,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        UserRegistrationForm userRegistrationForm = new UserRegistrationForm(nickName, email, name, password);
        userService.registerUser(userRegistrationForm);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public ModelAndView userList(Map<String, Object> model) {
        List<UserShowForm> userShowFormList = userService.getMemberList();
        model.put("users", userShowFormList);
        return new ModelAndView("user/list", model);
    }

    @GetMapping("/users/{userId}")
    public ModelAndView getProfile(@PathVariable("userId") Long userId, Map<String, Object> model) {
        UserProfileInfo userProfileInfo = userService.findUserProfileById(userId);
        model.put("name", userProfileInfo.getName());
        model.put("email", userProfileInfo.getEmail());
        return new ModelAndView("user/profile", model);
    }

    @PostMapping("/users/{userId}/update")
    public String updateUser(@PathVariable("userId") Long userId,
                             @RequestParam("userId") String nickName,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password) {
        userService.updateUser(new ModifyingUserForm(userId, nickName, password, name, email));
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/form")
    public String modifyingForm(@PathVariable("userId") Long userId, Model model) {
        ModifyingUserForm modifyingUserForm = userService.findModifyingUserForm(userId);
        model.addAttribute("user", modifyingUserForm);
        return "/user/updateForm";
    }

    @GetMapping("/user/form")
    public String userForm() {
        return "/resources/static/user/form";
    }

    @GetMapping("/user/login")
    public String userLogin() {
        return "/resources/static/user/login";
    }
}
