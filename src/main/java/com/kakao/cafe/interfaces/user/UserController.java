package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@link User} 목록 조회
     *
     * @param model
     */
    @GetMapping("/users")
    public String userList(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users_list";
    }

    /**
     * {@link User} 프로필 조회
     *
     * @param id    조회할 유저의 ID
     * @param model
     */
    @GetMapping("/users/{id}")
    public String userProfile(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("user", user);
        return "users_profile";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(String email, String nickname, String password) {
        User user = userRepository.add(new User(email, nickname, password));
        logger.info("[유저 가입] {}", user);
        return "redirect:/users";
    }
}
