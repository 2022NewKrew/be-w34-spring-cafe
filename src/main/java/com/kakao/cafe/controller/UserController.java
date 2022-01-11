package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserElementDto;
import com.kakao.cafe.dto.UserUpdateDto;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", getUserElementDtos());
        return "user/list";
    }

    @PostMapping
    public String register(User user) {
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        User user = findUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/{userId}/update")
    public String showUpdateUserInformation(@PathVariable String userId, Model model) {
        User user = findUser(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/{userId}/update")
    public String updateUserInformation(@PathVariable String userId, UserUpdateDto userUpdateDto) {
        User user = findUser(userId);
        if (user.getPassword().equals(userUpdateDto.getPassword())) {
            user.setPassword(userUpdateDto.getPassword());
            user.setName(userUpdateDto.getName());
            user.setEmail(userUpdateDto.getEmail());
        }
        return "redirect:/users";
    }

    private User findUser(String targetId) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(targetId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾고자 하는 사용자가 없습니다!"));
    }

    private List<UserElementDto> getUserElementDtos() {
        return IntStream
                .range(0, users.size())
                .mapToObj(index -> new UserElementDto(users.get(index), index + 1))
                .collect(Collectors.toList());
    }
}
