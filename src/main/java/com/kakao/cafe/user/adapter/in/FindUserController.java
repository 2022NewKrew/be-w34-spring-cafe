package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.FindUserQuery;
import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class FindUserController {

    private final FindUserQuery findUserQuery;
    private final Logger logger = LoggerFactory.getLogger(FindUserController.class);

    public FindUserController(FindUserQuery findUserQuery) {
        this.findUserQuery = findUserQuery;
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        FoundUserDto user = findUserQuery.find(new UserId(userId));
        model.addAttribute("user", user);
        logger.info("[Log] 유저의 프로필을 조회합니다. {}", userId);
        return "user/profile";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<FoundUserDto> users = findUserQuery.findAll();
        model.addAttribute(model.addAttribute("users", users));
        return "user/list";
    }
}
