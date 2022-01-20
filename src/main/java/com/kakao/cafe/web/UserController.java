package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUsers(Model model, HttpServletRequest request) {
        logger.info(request.getMethod() + " " + request.getRequestURI());
        model.addAttribute("users", userRepository.getUserList());
        return "users/list";
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable String id, Model model, HttpServletRequest request)
            throws InvalidUserProfileException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());
        try {
            User user = userRepository.findUserWithId(id);
            model.addAttribute("user", user);
        } catch(EmptyResultDataAccessException e) {
            throw new InvalidUserProfileException();
        }
        return "users/profile";
    }

    @PostMapping("/users")
    public String postUser(User user, HttpServletRequest request)
            throws InvalidUserIdException, DuplicateUserIdException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());
        try {
            userRepository.addUser(user);
        } catch(IllegalArgumentException e) {
            throw new InvalidUserIdException();
        } catch(DataIntegrityViolationException e) {
            throw new DuplicateUserIdException();
        }
        return "redirect:/users";
    }

    // 로그인 페이지 요청 url
    @GetMapping("/login")
    public String getLogin() {
        return "users/login";
    }

    // 로그인 실행 요청 url
    @PostMapping("/login")
    public String login(String id, String password, HttpSession session, HttpServletRequest request)
            throws NoUserAndPasswordException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        try {
            User user = userRepository.findUserWithIdAndPassword(id, password);
            session.setAttribute("sessionedUser", user);
        } catch(EmptyResultDataAccessException e) {
            throw new NoUserAndPasswordException();
        }
        return "redirect:/users";
    }

    // 로그아웃 실행 요청 url
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users";
    }
}
