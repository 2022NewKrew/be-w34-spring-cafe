package com.kakao.cafe.controller.users;


import com.kakao.cafe.common.exception.custom.UpdateFailedException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.common.session.SessionKeys;
import com.kakao.cafe.controller.users.dto.request.UserUpdateRequest;
import com.kakao.cafe.controller.users.dto.request.UserSignUpRequest;
import com.kakao.cafe.controller.users.mapper.UserViewMapper;
import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.service.user.dto.UserInfo;
import com.kakao.cafe.service.user.dto.UserSignUpForm;
import com.kakao.cafe.service.user.dto.UserUpdateForm;
import com.kakao.cafe.service.user.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserViewMapper userViewMapper;
    private final UserDtoMapper userDtoMapper;

    @GetMapping()
    public String list(Model model) {
        List<UserInfo> users = userService.getUsersAll();
        model.addAttribute("users", userViewMapper.toUserItemResponseList(users));
        return "user/list";
    }

    @PostMapping()
    public String signUp(UserSignUpRequest userSignUpRequest) {
        UserSignUpForm userSignUpForm = userDtoMapper.toUserSignForm(userSignUpRequest.getUserId(), userSignUpRequest.getPassword(),
                userSignUpRequest.getName(), userSignUpRequest.getEmail());
        userService.signUp(userSignUpForm);
        return "redirect:/users";
    }

    @GetMapping("{userId}")
    public String profile(@PathVariable String userId, Model model) {
        UserInfo userInfo = userService.getUserInfo(userId);
        model.addAttribute("user", userViewMapper.toUserProfileDto(userInfo));
        return "user/profile";
    }


    @GetMapping("{userId}/form")
    public String showUpdateForm(@PathVariable String userId,
                                 Model model,
                                 @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        validateUpdateUserId(loginInfo, userId);
        UserInfo userInfo = userService.getUserInfo(userId);
        model.addAttribute("user", userViewMapper.toUserUpdateFormResponse(userInfo));
        return "user/updateForm";
    }


    @PostMapping("{userId}/update")
    public String updateUser(@PathVariable String userId,
                             UserUpdateRequest userUpdateRequest,
                             @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        validateUpdateUserId(loginInfo, userId);
        UserUpdateForm userUpdateForm = userDtoMapper.toUserUpdateForm(userUpdateRequest);
        userService.update(userUpdateForm);
        return "redirect:/users";
    }

    private void validateUpdateUserId(UserIdentification loginInfo, String userId) {
        if(!loginInfo.getUserId().equals(userId)) {
            throw new UpdateFailedException(ErrorCode.UPDATE_USER_ID_INCORRECT);
        }
    }
}
