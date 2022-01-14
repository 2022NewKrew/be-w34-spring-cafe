package com.kakao.cafe.web.user;

import com.kakao.cafe.domain.user.UserSignUpService;
import com.kakao.cafe.domain.user.UserUpdateService;
import com.kakao.cafe.infra.repository.user.UserRepository;
import com.kakao.cafe.web.user.form.ModifyingUserInfo;
import com.kakao.cafe.web.user.form.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserUpdateService userUpdateService;
    private final UserSignUpService userSignUpService;

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserUpdateService userUpdateService, UserSignUpService userSignUpService, UserRepository userRepository) {
        this.userUpdateService = userUpdateService;
        this.userRepository = userRepository;
        this.userSignUpService = userSignUpService;
    }

    @GetMapping("/user/form")
    public String userRegisterForm(){
        return "user/form";
    }

    @PostMapping("/users")
    public String registerUser(@Valid @ModelAttribute UserRegistrationForm userRegistrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "user/form";
        userSignUpService.saveUser(userRegistrationForm);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findUserInventoryInfo());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("profile", userRepository.findUserProfileInfo(userId));
        return "user/profile";
    }

    @PostMapping("/users/{userId}/update")
    public String updateUser(@PathVariable("userId") Long userId,
                             @Valid @ModelAttribute ModifyingUserInfo modifyingUserInfo,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/users/" + userId + "/form";
        }
        userUpdateService.updateUser(userId, modifyingUserInfo);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/form")
    public String modifyingForm(@PathVariable("userId") Long userId, Model model) {
        ModifyingUserInfo modifyingUserInfo = userRepository.findModifyingUserForm(userId);
        model.addAttribute("user", modifyingUserInfo);
        return "user/updateForm";
    }

}
