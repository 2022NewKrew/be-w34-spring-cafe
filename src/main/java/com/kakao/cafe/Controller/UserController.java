package com.kakao.cafe.Controller;

import com.kakao.cafe.Model.UserDTO;
import com.kakao.cafe.Model.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserList userList = new UserList();

    @PostMapping("/user")
    public String singUp(UserDTO userDTO) {
        userList.add(userDTO);
        logger.info("{}(nickname) signed up", userDTO.getNickName());
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getList(Model model) {
        logger.info("GET /user : Get all users");

        model.addAttribute("UserList", userList);
        return "/users/list";
    }

    @GetMapping("/user/{userIdx}")
    public String getProfile(@PathVariable("userIdx") int userIdx,
                             Model model) {
        logger.info("GET /user/{} : View user({}) profile", userIdx, userIdx);

        UserDTO userDTO = userList.getUserList().get(userIdx - 1);
        model.addAttribute("user", userDTO);
        return "/users/profile";
    }
}
