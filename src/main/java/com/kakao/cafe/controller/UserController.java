package com.kakao.cafe.controller;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserService;
import com.kakao.cafe.user.dto.UserCreateDto;
import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public String insertUser(@ModelAttribute("user") @Valid UserCreateDto userCreateDto) {

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

        System.out.println(userUpdateDto.getUserId() +" userId");
        //TODO: model Mapper 매핑에러
        //User user = modelMapper.map(userUpdateDto, User.class);

        User user = new User();
        user.setId(id);
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        user.setUserId(userUpdateDto.getUserId());


        if (!userService.update(user)) {
            log.info("회원 정보 수정 실패 에러페이지로 이동");
        }

        return "redirect:/";
    }

}
