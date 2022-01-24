package com.kakao.cafe.controller;

import com.kakao.cafe.common.auth.Auth;
import com.kakao.cafe.common.exception.BaseException;
import com.kakao.cafe.controller.common.LoginUser;
import com.kakao.cafe.controller.common.SessionLoginUser;
import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserService;
import com.kakao.cafe.user.UserStatus;
import com.kakao.cafe.user.dto.UserCreateDto;
import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserLoginDto;
import com.kakao.cafe.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.common.auth.Auth.Role;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final SessionLoginUser sessionLoginUser;

    // TODO : 저장할 때 단방향 해시 필요
    @PostMapping(value = "/create")
    public String insertUser(@ModelAttribute("user") @Valid UserCreateDto userCreateDto, Errors errors, Model model) throws BaseException, SQLException {

        // 유효성 에러 발생시 회원가입 폼으로 유효성 메세지와 함께 전송
        if (errors.hasErrors()) {
            updateModelErrorStatus(userCreateDto, errors, model);
            return "user/form";
        }

        User user = UserCreateDto.toEntity(userCreateDto);

        userService.save(user);

        return "redirect:/";
    }

    private void updateModelErrorStatus(UserCreateDto userCreateDto, Errors errors, Model model) {

        model.addAttribute("valid", userCreateDto);

        Map<String, String> validateResults = UserCreateDto.validateHandling(errors);

        for (String key : validateResults.keySet()) {
            model.addAttribute(key, validateResults.get(key));
        }
    }

    @Auth(role = Role.ADMIN)
    @GetMapping
    public String viewUserList(Model model) {

        List<UserDto> users = UserDto.of(userService.findAll());

        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/create")
    public String viewUserCreateForm() {
        return "user/form";
    }

    @GetMapping("/checkAuth")
    public String viewUserPasswordCheckForm() {
        return "user/check_auth_form";
    }

    @GetMapping("/{id}/profile")
    public String viewUserProfile(@PathVariable("id") Long id, Model model) {

        UserDto user = modelMapper.map(userService.findOne(id), UserDto.class);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @Auth(role = Role.ADMIN)
    @GetMapping("/{id}/update")
    public String viewUserAdminUpdateForm(@PathVariable("id") Long id, Model model) {

        User user = userService.findOne(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        model.addAttribute("user", userDto);

        return "user/update_admin_form";
    }

    @PostMapping("/updateAuth")
    public String viewUserUpdateForm(Model model, @RequestParam String password) throws BaseException {

        LoginUser loginUser = sessionLoginUser.getLoginUser();
        User user = userService.loginCheck(loginUser.getUserId(), password);

        UserDto userDto = modelMapper.map(user, UserDto.class);

        model.addAttribute("user", userDto);

        return "user/update_form";
    }

    @Auth(role = Role.ADMIN)
    @PutMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserUpdateDto userUpdateDto) throws BaseException {

        User user = new User(id, userUpdateDto.getName(), userUpdateDto.getEmail());

        if (!userService.update(user)) {
            log.info("회원 정보 수정 실패 에러페이지로 이동");
            throw new BaseException("ADMIN : 회원 정보 수정 실패");
        }

        return "redirect:/";
    }

    @PutMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid UserUpdateDto userUpdateDto) throws BaseException {

        LoginUser loginUser = sessionLoginUser.getLoginUser();

        User user = new User(loginUser.getId(), userUpdateDto.getName(), userUpdateDto.getEmail());

        if (!userService.update(user)) {
            log.info("회원 정보 수정 실패 에러페이지로 이동");
            throw new BaseException("회원 정보 수정 실패");
        }

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid UserLoginDto userLoginDto) throws BaseException {

        User user = userService.loginCheck(userLoginDto.getUserId(), userLoginDto.getPassword());

        setLoginUserSession(user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        sessionLoginUser.invalidate();
        return "redirect:/";
    }

    private void setLoginUserSession(User user) {

        LoginUser userDto = modelMapper.map(user, LoginUser.class);

        sessionLoginUser.setLoginUser(userDto);

        if (user.getRole() == UserStatus.ADMIN) {
            sessionLoginUser.setAdmin();
        }
    }

}
