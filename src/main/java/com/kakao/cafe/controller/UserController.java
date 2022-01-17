package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String userList(@RequestParam(value="error", required = false, defaultValue = "none") String error,
                           Model model) {
        if (!error.equals("none")) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", error);
        }
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/login")
    public String userLoginForm(@RequestParam(value="error", required = false, defaultValue = "false") Boolean error,
                                Model model) {
        model.addAttribute("user", new UserDTO());
        if (error) {
            model.addAttribute("error", error);
        }
        return "user/login";
    }

    @PostMapping("/login")
    public String userLogin(UserDTO userDTO, HttpSession session) {
        Optional<User> result = userService.login(userDTO);
        if(result.isEmpty()) {
            return "redirect:/user/login?error=true";
        }
        session.setAttribute("sessionedUser", result.get());
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String userSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String userSignUp(UserDTO userDTO) {
        userService.join(userDTO);
        return "redirect:/user";
    }

    @GetMapping("/signout")
    public String userSignOut(HttpSession session) {
        session.removeAttribute("sessionedUser");
        return "redirect:/";
    }

    @GetMapping("/{key}")
    public String userProfile(@PathVariable Long key, Model model) {
        Optional<UserDTO> userDTO = userService.findByKeyDTO(key);
        if (userDTO.isEmpty()) {
            log.info("user doesn't exist");
            return "redirect:/user";
        }
        model.addAttribute("user", userDTO.get());
        return "user/profile";
    }

    @GetMapping("/{key}/update")
    public String userUpdateForm(@PathVariable Long key, Model model, HttpSession session) {
        Object sessionData = session.getAttribute("sessionedUser");
        if (sessionData == null || (sessionData != null && ((User)sessionData).getKey() != key)) {
            return "redirect:/user?error=NO PERMISSION";
        }
        Optional<UserDTO> userDTO = userService.findByKeyDTO(key);
        if (userDTO.isEmpty()) {
            log.info("user doesn't exist");
            return "redirect:/user";
        }
        model.addAttribute("user", userDTO.get());
        return "user/form";
    }

    @PostMapping("/{key}/update")
    public String userUpdate(@PathVariable Long key, UserDTO userDTO) {
        userService.updateByKey(key, userDTO);
        return "redirect:/user";
    }
}
