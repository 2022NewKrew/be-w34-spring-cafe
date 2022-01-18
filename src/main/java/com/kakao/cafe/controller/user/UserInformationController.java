package com.kakao.cafe.controller.user;

import com.kakao.cafe.controller.user.dto.UserElementDto;
import com.kakao.cafe.controller.user.dto.UserInformationDto;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserInformationController {

    private final UserService userService;

    public UserInformationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.getUsers();
        List<UserElementDto> userElementDtos = userToUserElementDto(users);
        model.addAttribute("users", userElementDtos);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findUserByUserId(userId);
        UserInformationDto userInformationDto = new UserInformationDto(user);
        model.addAttribute("user", userInformationDto);
        return "user/profile";
    }

    private List<UserElementDto> userToUserElementDto(List<User> users) {
        return IntStream
                .range(0, users.size())
                .mapToObj(index -> new UserElementDto(users.get(index), index + 1))
                .collect(Collectors.toList());
    }
}
