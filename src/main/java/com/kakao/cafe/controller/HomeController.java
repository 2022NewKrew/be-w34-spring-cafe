package com.kakao.cafe.controller;


import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        logger.info("index");
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            model.addAttribute("currentUser", user);
            logger.info("User session get {}", user);
        }
        return "index";
    }


}
