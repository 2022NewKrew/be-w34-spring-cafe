package com.kakao.cafe.controller;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    // TODO : 저장할 때 단방향 해시 필요
    @PostMapping(value = "/create")
    public String insertUser(@ModelAttribute("user") @Valid UserCreateDto userCreateDto, Errors errors, Model model) {

        // 유효성 에러 발생시 회원가입 폼으로 유효성 메세지와 함께 전송
        if (errors.hasErrors()) {
            model.addAttribute("valid", userCreateDto);

            Map<String, String> validateResults = UserCreateDto.validateHandling(errors);

            for (String key : validateResults.keySet()) {
                model.addAttribute(key, validateResults.get(key));
            }

            return "user/form";
        }

        User user = modelMapper.map(userCreateDto, User.class);
        Long id = userService.save(user);

        return "redirect:/";
    }

    @GetMapping
    public String viewUserList(Model model) {

        List<UserDto> users = userService.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());

        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/create")
    public String viewUserCreateForm() {
        return "user/form";
    }

    @GetMapping("/{id}/update")
    public String viewUserUpdateForm(@PathVariable("id") Long id, Model model) {

        User user = userService.findOne(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        model.addAttribute("user", userDto);

        return "user/update_form";
    }

    @PutMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserUpdateDto userUpdateDto, Model model) {

        //TODO: model Mapper 매핑에러
        //User user = modelMapper.map(userUpdateDto, User.class);

        User user = new User();
        user.setId(id);
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());


        if (!userService.update(user)) {
            log.info("회원 정보 수정 실패 에러페이지로 이동");
        }

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @ModelAttribute("user") @Valid UserLoginDto userLoginDto, Model model) {

        User user = userService.loginCheck(userLoginDto.getUserId(), userLoginDto.getPassword());

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);
        }

        return "redirect:/";
    }

}
