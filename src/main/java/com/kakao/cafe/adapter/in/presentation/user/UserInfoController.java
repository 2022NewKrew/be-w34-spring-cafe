package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private static final String VIEWS_USER_UPDATE_FROM = "user/updateForm";

    private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

    private final GetUserInfoUseCase getUserInfoUseCase;

    public UserInfoController(GetUserInfoUseCase getUserInfoUseCase) {
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    @GetMapping("/users")
    public String displayUserList(HttpServletRequest request, Model model) {
        log.info("[{}]User list required", request.getRequestURI());
        model.addAttribute("users", getUserInfoUseCase.getAllUsersInfo().getUserList());
        log.info("[{}]Success show user list", request.getRequestURI());
        return VIEWS_USER_LIST;
    }

    @GetMapping("/users/{userId}")
    public String displayUserProfile(HttpServletRequest request, @PathVariable String userId, Model model)
        throws UserNotExistException {
        log.info("[{}]User {} information required", request.getRequestURI(), userId);
        UserInfo userInfo = getUserInfoUseCase.getUserProfile(userId);
        log.info("[{}]Success show user {} information", request.getRequestURI(), userId);
        model.addAttribute("name", userInfo.getName());
        model.addAttribute("email", userInfo.getEmail());
        return VIEWS_USER_PROFILE;
    }

    @GetMapping(path = {"/users/{userId}/form", "/users/{userId}/form/error"})
    public String displayUserUpdateForm(HttpServletRequest request, @PathVariable String userId, Model model, HttpSession session)
        throws UserNotExistException, UnauthenticatedUserException {
        log.info("[{}]{} update form required", request.getRequestURI(), userId);
        String sessionedUserId = (String) session.getAttribute("sessionedUser");
        if (!sessionedUserId.equals(userId)) {
            log.info("[{}]{} is do not have permission.", request.getRequestURI(), sessionedUserId);
            throw new UnauthenticatedUserException("인증 오류");
        }
        UserInfo userInfo = getUserInfoUseCase.getUserProfile(userId);
        log.info("[{}]Success show user {} update form", request.getRequestURI(), userId);
        model.addAttribute("user", userInfo);
        return VIEWS_USER_UPDATE_FROM;
    }
}
