package com.kakao.cafe.user.web;

import com.kakao.cafe.exception.InvalidDtoException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.user.service.UserService;
import com.kakao.cafe.user.web.dto.UserSaveDto;
import com.kakao.cafe.user.web.dto.UserShowDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String userList(Model model) {
        List<UserShowDto> userShowDtoList = userService.findAllUser();
        model.addAttribute("users", userShowDtoList);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String userDetail(@PathVariable("userId") String userId, Model model) {
        UserShowDto userShowDto = userService.findUser(userId)
            .orElseThrow(UserNotFoundException::new);
        model.addAttribute("user", userShowDto);
        return "user/profile";
    }

    @PostMapping
    public String userAdd(@Valid UserSaveDto userSaveDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDtoException(bindingResult);
        }
        userService.addUser(userSaveDto);
        return "redirect:/users";
    }
}

