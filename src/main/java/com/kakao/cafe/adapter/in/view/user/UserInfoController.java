package com.kakao.cafe.adapter.in.view.user;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserInfoController {

    private static final String VIEWS_USER_LIST = "user/list";
    private static final String VIEWS_USER_PROFILE = "user/profile";

    private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

    private final GetUserInfoUseCase getUserInfoUseCase;

    public UserInfoController(GetUserInfoUseCase getUserInfoUseCase) {
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    @GetMapping("/users")
    public String displayUserList(Model model) {
        model.addAttribute("users", getUserInfoUseCase.getAllUsersInfo().getUserList());
        return VIEWS_USER_LIST;
    }

    @GetMapping("/users/{userId}")
    public String displayUserProfile(@PathVariable String userId, Model model) {
        UserInfo userInfo = getUserInfoUseCase.getUserProfile(userId);
        if (!userInfo.getUserId().equals("")) {
            log.info("{} information required", userId);
            model.addAttribute("name", userInfo.getName());
            model.addAttribute("email", userInfo.getEmail());
            return VIEWS_USER_PROFILE;
        }
        log.warn("{} is not in User List", userId);
        return "redirect:/users";
    }
}
