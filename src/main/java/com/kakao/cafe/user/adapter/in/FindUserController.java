package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.FindUserService;
import com.kakao.cafe.user.application.port.in.FindUserDto;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class FindUserController {

    private final FindUserService findUserService;
    private final Logger logger = LoggerFactory.getLogger(FindUserController.class);

    public FindUserController(FindUserService findUserService) {
        this.findUserService = findUserService;
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        FindUserDto user = findUserService.find(new UserId(userId));
        model.addAttribute("user", user);
        logger.info("[Log] 유저의 프로필을 조회합니다. {}", userId);
        return "user/profile";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<FindUserDto> users = findUserService.findAll();
        model.addAttribute(model.addAttribute("users", users));
        return "user/list";
    }
}
