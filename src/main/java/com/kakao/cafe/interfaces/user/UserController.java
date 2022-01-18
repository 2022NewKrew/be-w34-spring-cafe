package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.common.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@link User} 목록 조회
     *
     * @param model
     */
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "user_list";
    }

    /**
     * {@link User} 프로필 조회
     *
     * @param id    조회할 유저의 ID
     * @param model
     */
    @GetMapping("/{id}")
    public String userProfile(@PathVariable long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_profile";
    }

    /**
     * 회원가입
     *
     * @param userDto 회원가입시 입력한 정보
     */
    @PostMapping("/new")
    public String signup(UserDto userDto) {
        logger.info("[유저 가입] {}", userDto);
        userService.signup(userDto);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public RedirectView login(
            UserDto userDto,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        logger.info("[로그인 시도] {}", userDto);

        try {
            User loginUser = userService.login(userDto);
            session.setAttribute("loginUser", loginUser);
            redirectAttributes.addFlashAttribute("flashMessage", "로그인 되었습니다.");
            return new RedirectView("/", true);
        } catch (NoSuchElementException e) {
            logger.info("[로그인 실패] {}", userDto);
            redirectAttributes.addFlashAttribute("flashMessage", "로그인에 실패했습니다");
            return new RedirectView("/login", true);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}
