package com.kakao.cafe.controller;


import com.kakao.cafe.form.LoginForm;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.form.UserCreationForm;
import com.kakao.cafe.form.UserEditForm;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/createAccount")
    public String createForm(){
        return "user/form";
    }

    @PostMapping("/users")
    public String createAccount(UserCreationForm form){
        logger.info("POST /users : {} 생성", form.getEmail());
        User user = new User();
        user.setPassword(form.getPassword());
        user.setNickname(form.getNickname());
        user.setEmail(form.getEmail());
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        logger.info("GET /users : 유저 전체목록 조회");
        List<User> users = userService.findMembers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{ID}")
    public String getUser(@PathVariable int ID, Model model){
        logger.info("GET /users/{} : {} 유저 조회",ID,ID);
        Optional<User> user = userService.findOne(ID);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users/{ID}/update")
    public String getUserEditForm(@PathVariable int ID, Model model,HttpSession session){
        logger.info("GET /users/{}/form : {} 유저 개인정보 수정",ID,ID);
        Object value = session.getAttribute("sessionedUser");
        if(value != null){
            Optional<User> user = userService.findOne(ID);
            try{
                User u = user.get();
                model.addAttribute("user",u);
                return "index";
            } catch(Exception e){
                logger.info("null user 에러");
                return "user/unknown-user";
            }
        } else{
            logger.info("unknown user error");
            return "user/unkown-user";
        }
    }

    @PutMapping("/users/{ID}/update")
    public String editUser(@PathVariable int ID, UserEditForm form){
        logger.info("POST /users/{}/update : {} 유저 개인정보 수정",ID,ID);
        Optional<User> user = userService.findOne(ID);
        try{
            User u = user.get();
            u.setNickname(form.getNickname());
            u.setEmail(form.getEmail());
            userService.editUser(u);
        } catch (Exception e){
            logger.info("null user 에러");
            return "user/unknown-user";
        }

        return "user/list";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, LoginForm form){
        logger.info("{} 로그인", form.getEmail());
        if(userService.validateLogin(form.getEmail(),form.getPassword())){
            session.setAttribute("sessionedUser",userService.findOneByEmail(form.getEmail()));
            return "redirect:/";
        } else{
            logger.info("로그인 에러");
            return "redirect:/user/login-error";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        Object value = session.getAttribute("sessionedUser");
        if(value != null){
            User user = (User)value;
            logger.info("{} 로그아웃 : ", user.getEmail());
            session.invalidate();
            return "redirect:/";
        } else{
            logger.info("logout error");
            return "user/login-error";
        }
    }
}
