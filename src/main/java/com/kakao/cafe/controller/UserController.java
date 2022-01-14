package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDTO;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.dto.user.UserCreationDTO;
import com.kakao.cafe.util.Url;
import com.kakao.cafe.util.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final Logger logger;
    @Autowired
    private UserService userService;

    public UserController() {
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return View.USER_LIST;
    }

    @PostMapping("/users")
    public String createUser(@Valid UserCreationDTO userCreationDTO,
                             Errors errors,
                             RedirectAttributes attr) {
        try {
            validateParams(errors);
            userService.join(userCreationDTO);
        } catch (Exception e) {
            handleCommonException(e, attr);
            attr.addFlashAttribute("email", userCreationDTO.getEmail());
            attr.addFlashAttribute("nickname", userCreationDTO.getNickname());
            return "redirect:" + Url.REGISTER_FORM;
        }

        return "redirect:" + Url.USERS;
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable("id") Long id,
                                 Model model,
                                 RedirectAttributes attr) {
        try {
            model.addAttribute("user", getUser(id));
        } catch (IllegalArgumentException e) {
            handleSpecificException(e, attr);
            return "redirect:" + Url.USERS;
        }

        return View.USER_PROFILE;
    }


    @GetMapping("/users/{id}/form")
    public String getProfileUpdateForm(@PathVariable("id") Long id,
                                       Model model,
                                       RedirectAttributes attr) {
        try {
            model.addAttribute("user", getUser(id));
        } catch (IllegalArgumentException e) {
            handleSpecificException(e, attr);
            return "redirect:" + Url.USERS;
        }

        return View.USER_UPDATE_FORM;
    }

    @PostMapping("/users/{id}/update")
    public String updateUserProfile(@PathVariable("id") Long id,
                                    @Valid UserCreationDTO dto,
                                    Errors errors,
                                    RedirectAttributes attr) {
        try {
            validateParams(errors);
            userService.update(id, dto);
        } catch (IllegalArgumentException e) {
            handleSpecificException(e, attr);
            return "redirect:/users/" + id + "/form";
        } catch (Exception e) {
            handleCommonException(e, attr);
            return "redirect:/users/" + id + "/form";
        }

        return "redirect:" + Url.USERS;
    }

    @PostMapping("/user/login")
    public String login() {
        //미구현 상태
        return "redirect:" + Url.INDEX;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        //미구현 상태
        return "redirect:" + Url.USER_LOGIN;
    }

    private UserDTO getUser(Long id) {
        return userService.findById(id);
    }

    private void validateParams(Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> logger.warn("{}", e.getDefaultMessage()));
            throw new IllegalArgumentException("입력값이 올바르지 않습니다");
        }
    }

    private void handleSpecificException(Exception e, RedirectAttributes attr) {
        handleException(e, attr, e.getMessage());
    }

    private void handleCommonException(Exception e, RedirectAttributes attr) {
        handleException(e, attr, e.getMessage());
    }

    private void handleException(Exception e, RedirectAttributes attr, String msg){
        e.printStackTrace();
        attr.addFlashAttribute("errorMsg", msg);
    }
}
