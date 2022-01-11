package com.kakao.cafe.users;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;


@RestController
public class UserController {
    private ArrayList<User> users = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/createAccount")
    public String createAccount(String ID, String password, String nickname, String email){
        logger.info("POST /createAccount : {} 생성", ID);
        User user = new User(ID,password,nickname,email);
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        logger.info("GET /users : 유저 전체목록 조회");
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{ID}")
    public String getUser(@PathVariable String ID, Model model){
        logger.info("GET /users/{} : {} 유저 조회",ID,ID);
        User profile = null;
        for(User user : users){
            if(user.getID() == ID){
                profile = user;
            }
        }
        model.addAttribute("profile", profile);
        return "user/profile";
    }

    @GetMapping("/users/{ID}//form")
    public String getUserEditForm(@PathVariable String ID, Model model){
        logger.info("GET /users/{}/form : {} 유저 개인정보 수정",ID,ID);
        User profile = null;
        for(User user: users){
            if(Objects.equals(user.getID(), ID)){
                profile = user;
            }
        }
        model.addAttribute("user",profile);
        return "/user/updateForm";
    }

    @PostMapping("/users/{ID}/update")
    public String editUser(@PathVariable String ID, String password, String nickname, String email){
        logger.info("POST /users/{}/update : {} 유저 개인정보 수정",ID,ID);
        for(int i=0;i<users.size();i++){
            if(Objects.equals(users.get(i).getID(), ID)){
                User editedUser = new User(ID, password, nickname, email);
                users.set(i,editedUser);
            }
        }
        return "user/list";
    }
}
