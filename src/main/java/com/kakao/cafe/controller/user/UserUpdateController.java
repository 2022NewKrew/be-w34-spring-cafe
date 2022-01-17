package com.kakao.cafe.controller.user;

import com.kakao.cafe.controller.user.dto.UserInformationDto;
import com.kakao.cafe.controller.user.dto.UserUpdateDto;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserUpdateController {

    private final UserService userService;

    public UserUpdateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/update")
    public String showUpdateUserInformation(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loginUserId");
        User user = userService.findUserByUserId(userId);
        UserInformationDto userInformationDto = new UserInformationDto(user);
        model.addAttribute("user", userInformationDto);
        return "user/updateForm";
    }

    @PostMapping("/users/update")
    public String updateUserInformation(String password, UserUpdateDto userUpdateDto,
            HttpSession session) {
        userService.updateUser(
                (String) session.getAttribute("loginUserId"),
                password,
                userUpdateDto.getName(),
                userUpdateDto.getEmail()
        );
        return "redirect:/";
    }
}
