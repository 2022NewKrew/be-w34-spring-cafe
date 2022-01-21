package com.kakao.cafe.controller;

import com.kakao.cafe.controller.error.ErrorMessageBox;
import com.kakao.cafe.core.exception.NoSuchUser;
import com.kakao.cafe.domain.user.dto.UserUpdateForm;
import com.kakao.cafe.domain.user.dto.UserResponse;
import com.kakao.cafe.domain.user.dto.UserJoinForm;
import com.kakao.cafe.domain.user.dto.UserListDto;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Validator validator;

    @PostMapping("")
    public String createUser(@Valid @ModelAttribute UserJoinForm userDto, BindingResult bindingResult, Model model) {
        System.out.println("error exists? : "+bindingResult.hasErrors());
        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            model.addAttribute("messages", errorMessages);
            return "user/form";
        }

        try{
            userService.join(userDto);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("messages", "사용자 id 중복");
            return "user/form";
        }
    }

    @GetMapping({"", "/list"})
    public String userList(@SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user, Model model) {
        List<UserListDto> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable("id") String userId, Model model) {
        try{
            User user = userService.findUser(userId);
            UserResponse dtoUser = UserResponse.from(user);
            model.addAttribute("user", dtoUser);
            return "user/profile";
        } catch (NoSuchUser e) {
            return ErrorMessageBox.handling("사용자 없음.", model);
        }
    }

    @GetMapping("/update")
    public String showForm(@SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user, Model model) {
        UserResponse dtoUser = UserResponse.from(user);
        model.addAttribute("user", dtoUser);
        return "user/updateForm";
    }

    @PutMapping("")
    public String updateForm(@Valid @ModelAttribute UserUpdateForm updateUser, @SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user) {
        userService.updateUser(user, updateUser);
        return "redirect:/users";
    }
}
