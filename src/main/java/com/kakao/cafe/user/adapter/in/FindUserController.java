package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.FindUserUseCase;
import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class FindUserController {

    private final FindUserUseCase findUserUseCase;
    private final Logger logger = LoggerFactory.getLogger(FindUserController.class);

    public FindUserController(FindUserUseCase findUserUseCase) {
        this.findUserUseCase = findUserUseCase;
    }

    @GetMapping("/{userId}")
    public String showUser(@PathVariable long userId, Model model) {
        FoundUserDto foundUserDto = findUserUseCase.find(new UserId(userId));
        ViewUserDto viewUserDto = foundUserDto.toViewUserDto();

        model.addAttribute("user", viewUserDto);
        logger.info("[Log] 유저의 프로필을 조회합니다. {}", userId);
        return "/user/profile";
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        List<FoundUserDto> users = findUserUseCase.findAll();
        List<ViewUserDto> viewUserDtoList =
            users.stream().map(FoundUserDto::toViewUserDto)
                .collect(Collectors.toUnmodifiableList());

        model.addAttribute(model.addAttribute("users", viewUserDtoList));
        return "/user/list";
    }
}
