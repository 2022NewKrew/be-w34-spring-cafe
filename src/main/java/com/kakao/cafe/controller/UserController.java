package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.SessionUserInfoCheck;
import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.WrongPasswordException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {
    private static final String FAIL_LOGIN_MESSAGE = "이메일 혹은 비밀번호가 틀렸습니다.";
    private static final String ACCESS_DENIED_MESSAGE = "다른유저의 정보에는 접근할 수 없습니다.";
    @Resource(name = "userService")
    private UserService userService;

    @GetMapping
    String getUsers(Model model) {
        List<UserDTO> userList = userService.getUserList();
        model.addAttribute("users", userList);

        return "user/list";
    }

    @PostMapping("/create")
    String createUser(@Valid UserDTO user, Model model) {
        if (userService.insertUser(user) > 0) {
            log.info("create User -> UserId : {}, Email : {}", user.getUserId(), user.getEmail());
            return "redirect:/users";
        } else {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, "중복된 이메일 혹은 닉네임입니다.");
            return Constants.ERROR_PAGE_NAME;
        }

    }

    @GetMapping("/{id}/form")
    @SessionUserInfoCheck
    String getUserForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        if (!Objects.equals(id, user.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ACCESS_DENIED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }

        model.addAttribute("user", user);
        log.info("get User(Form) -> ID : {}, UserId : {}", user.getId(), user.getUserId());
        return "user/updateForm";
    }

    @PutMapping("/{id}/update")
    @SessionUserInfoCheck
    String updateUser(@PathVariable Long id, @Valid UserDTO user, Model model, HttpSession session) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        try {
            userService.updateUser(id, user, sessionUser.getPassword());
        } catch (NoChangeException | WrongPasswordException | NoModifyPermissionException exception) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, exception.getMessage());
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("update User -> Id : {}, Email : {}", id, user.getEmail());
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    String getUserProfile(@PathVariable long id, Model model) {
        UserDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        log.info("get User(Profile) -> ID : {}, UserId : {}", user.getId(), user.getUserId());

        return "user/profile";
    }

    @PostMapping("/loginProcess")
    String loginProcess(HttpSession session, @Valid LoginDTO login, Model model) {
        UserDTO user = userService.getUserByLoginData(login);
        if (user != null) {
            log.info("login success -> ID : {}, UserId : {}", user.getId(), user.getUserId());
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        }
        log.info("login fail -> email : {}", login.getEmail());
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, FAIL_LOGIN_MESSAGE);
        return Constants.ERROR_PAGE_NAME;
    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        if (user != null) {
            log.info("logout -> ID : {}, UserId : {}", user.getId(), user.getUserId());
            session.invalidate();
        }
        return "redirect:/";
    }

}
