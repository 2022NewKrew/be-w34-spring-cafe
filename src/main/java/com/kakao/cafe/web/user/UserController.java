package com.kakao.cafe.web.user;

import com.kakao.cafe.web.user.domain.User;
import com.kakao.cafe.web.user.domain.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserDao userDao;

    Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    private Users users = new Users();

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    // 회원가입 -> Post로 요청을 받아서 user 인스턴스 추가 후 redirect
    @PostMapping("/user/create")
    public String singUp(User user) {
        logger.info("user signup");
        userDao.createUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/form")
    public String getForm(){
        logger.info("getForm");
        return "user/form";
    }

    // 사용자 리스트 view에 userList전달
    @GetMapping("/user/list")
    public String getList(Model model){
        logger.info("getList");
        model.addAttribute("users", userDao.readUsers());
        return "user/list";
    }

    // 해당하는 사용자 검색 후 view에 user전달
    @GetMapping("/user/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        logger.info("getProfile");
        model.addAttribute("user", userDao.findUserById(Integer.valueOf(userId)));
        return "user/profile";
    }

    @GetMapping("/user/login")
    public String getLogin() {
        logger.info("getLogin");
        return "user/login";
    }

    // 로그인 (세션 생성)
    @PostMapping("/user/loginCheck")
    public String login(String email, String password, HttpSession session) {
        logger.info("loginCheck");
        User user = userDao.findUserByEmail(email);
        if (user.getPassword().equals(password)) {
            session.setAttribute("sessionedUser", user);
            return "index";
        }
        return "user/login_failed";
    }

    // 로그아웃 (세션 폐기)
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        logger.info("logout");
        session.invalidate();
        return "index";
    }
}
