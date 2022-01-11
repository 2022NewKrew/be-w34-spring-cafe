package com.kakao.cafe.Controller;

import com.kakao.cafe.DTO.ArticleList;
import com.kakao.cafe.DTO.UserDTO;
import com.kakao.cafe.DTO.UserList;
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
    private ArticleList articleList = new ArticleList();

    @PostMapping("/users")
    public String singUp(UserDTO userDTO){
        userList.add(userDTO);
        logger.info("{}(nickname) signed up", userDTO.getNickName());
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getList(Model model){
        logger.info("GET /list : Get all user list");

        model.addAttribute("UserList", userList);
        return "/user/list";
    }

    @GetMapping("/users/{userIdx}")
    public String getProfile(@PathVariable("userIdx") int userIdx,
                             Model model){
        logger.info("GET /users/{} : View user({}) profile",userIdx,userIdx);

        UserDTO userDTO = userList.getUserList().get(userIdx-1);
        model.addAttribute("user", userDTO);
        return "/user/profile";
    }
}
