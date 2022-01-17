package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersApiController {
    private final UserService userService;

    private UsersApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<Map<String, String>> userList = new ArrayList<>();
        List<Users> queriedUsers = userService.getUsers();
        for (int i = 0; i < queriedUsers.size(); i++) {
            Users user = queriedUsers.get(i);
            userList.add(
                    (Map.of("index", Integer.toString(i + 1),
                            "id", Integer.toString(user.getId()),
                            "userId", user.getUserId(),
                            "name", user.getName(),
                            "email", user.getEmail()))
            );
        }
        model.addAttribute("users", userList);
        return "users/list";
    }

    @GetMapping("/form")
    String getForm() {
        return "users/form";
    }

    @PostMapping("/login")
    String login(String userId, String password, HttpSession session) {
        try {
            Users user = userService.getByUserName(userId);
            if (!user.getPassword().equals(password)) {
                System.out.println("패스워드가 일치하지 않습니다.");
                return "redirect:/users/login";
            }
            session.setAttribute("sessionedUser", user);
            return "redirect:/";
        } catch (Exception e) {
            System.out.println("아이디가 존재하지 않습니다."); // TODO: 경고창 띄우는 것으로 변경
            return "users/login";
        }
    }

    @GetMapping("/login")
    String getLoginForm() {
        return "users/login";
    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/{id}")
    String findById(@PathVariable int id, Model model) {
        Users user = userService.getByUserId(id);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/profile")
    String getProfile(Users user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping
    String createUser(Users user, HttpSession session) {
        userService.addUser(user);
        session.setAttribute("sessionedUser", user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    String getUpdateForm(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        model.addAttribute("user", user);
        return "users/updateForm";
    }

    @PostMapping("/update")
    String updateUserProfile(String newPassword, Users updateUser, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");
        userService.updateUser(currentUser.getId(), updateUser, newPassword);
        return "redirect:/users";
    }
}
