package com.kakao.cafe.controller.user;

import com.kakao.cafe.model.user.User;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userToUserElementDto(userService.getUsers()));
        return "user/list";
    }

    @PostMapping
    public String register(UserRegisterDto userRegisterDto) {
        userService.createUser(
                userRegisterDto.getUserId(),
                userRegisterDto.getPassword(),
                userRegisterDto.getName(),
                userRegisterDto.getEmail()
        );
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        model.addAttribute("user", new UserInformationDto(userService.findUserByUserId(userId)));
        return "user/profile";
    }

    @GetMapping("/update")
    public String showUpdateUserInformation(Model model, HttpSession session) {
        model.addAttribute("user", new UserInformationDto(userService.findUserByUserId((String) session.getAttribute("userId"))));
        return "user/updateForm";
    }

    @PostMapping("/update")
    public String updateUserInformation(UserUpdateDto userUpdateDto, HttpSession session) {
        userService.updateUser(
                (String) session.getAttribute("userId"),
                userUpdateDto.getPassword(),
                userUpdateDto.getName(),
                userUpdateDto.getEmail()
        );
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpSession session) {
        if (userService.hasUser(userLoginDto.getUserId(), userLoginDto.getPassword())) {
            User user = userService.findUserByUserId(userLoginDto.getUserId());
            session.setAttribute("isLogin", true);
            session.setAttribute("userId", user.getUserId().getValue());
            session.setAttribute("name", user.getName().getValue());
            session.setAttribute("email", user.getEmail().getValue());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws HttpSessionRequiredException {
        checkSessionExist(session);

        session.invalidate();
        return "redirect:/";
    }

    private List<UserElementDto> userToUserElementDto(List<User> users) {
        return IntStream
                .range(0, users.size())
                .mapToObj(index -> new UserElementDto(users.get(index), index + 1))
                .collect(Collectors.toList());
    }

    private void checkSessionExist(HttpSession session) throws HttpSessionRequiredException {
        if (session.isNew()) {
            throw new HttpSessionRequiredException("로그인 상태가 아닙니다!, (세션이 존재하지 않습니다)");
        }
    }
}
