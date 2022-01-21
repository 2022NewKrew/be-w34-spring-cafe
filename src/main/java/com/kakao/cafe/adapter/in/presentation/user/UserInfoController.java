package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class UserInfoController {

    private static final String VIEWS_LOGIN = "user/login";
    private static final String VIEWS_SIGNUP_FORM = "user/form";
    private static final String VIEWS_USER_LIST = "user/list";
    private static final String VIEWS_USER_PROFILE = "user/profile";
    private static final String VIEWS_USER_UPDATE_FROM = "user/updateForm";

    private final GetUserInfoUseCase getUserInfoUseCase;

    public UserInfoController(GetUserInfoUseCase getUserInfoUseCase) {
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    @GetMapping("/users")
    public String displayUserList(Model model) {
        model.addAttribute("users", getUserInfoUseCase.getAllUsersInfo().getUserList());
        return VIEWS_USER_LIST;
    }

    @GetMapping("/user/login")
    public String displayLoginPage() {
        return VIEWS_LOGIN;
    }

    @GetMapping("/users/form")
    public String displaySignUpForm() {
        return VIEWS_SIGNUP_FORM;
    }

    @GetMapping("/users/{userId}")
    public String displayUserProfile(@PathVariable String userId, Model model)
        throws UserNotExistException {
        UserInfo userInfo = getUserInfoUseCase.getUserProfile(userId);
        model.addAttribute("name", userInfo.getName());
        model.addAttribute("email", userInfo.getEmail());
        return VIEWS_USER_PROFILE;
    }

    @GetMapping("/users/{userId}/form")
    public String displayUserUpdateForm(@PathVariable String userId, Model model, @RequestAttribute UserInfo sessionedUser)
        throws UserNotExistException, UnauthenticatedUserException {
        if (!sessionedUser.getUserId().equals(userId)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        UserInfo userInfo = getUserInfoUseCase.getUserProfile(userId);
        model.addAttribute("user", userInfo);
        return VIEWS_USER_UPDATE_FROM;
    }
}
