package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.application.user.UpdateUserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;
import com.kakao.cafe.interfaces.user.dto.UserMapper;
import com.kakao.cafe.interfaces.user.dto.request.JoinUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.request.UpdateUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final FindUserService findUserService;
    private final SignUpUserService signUpUserService;
    private final UpdateUserService updateUserService;

    public UserController(FindUserService findUserService, SignUpUserService signUpUserService, UpdateUserService updateUserService) {
        this.findUserService = findUserService;
        this.signUpUserService = signUpUserService;
        this.updateUserService = updateUserService;
    }

    @GetMapping("")
    public String getAllUser(Model model) {
        List<User> userList = findUserService.findAllUser();
        List<UserResponseDto> joinUserRequestDtoList = UserMapper.convertEntityListToResponseDtoList(userList);
        model.addAttribute("users", joinUserRequestDtoList);
        return "user/list";
    }

    @PostMapping("")
    public String joinUser(JoinUserRequestDto joinUserRequestDto) {
        UserVo user = UserMapper.convertJoinUserDtoToVo(joinUserRequestDto);
        signUpUserService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId(@PathVariable String userId, Model model) {
        User user = findUserService.findByUserId(userId);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());

        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public ModelAndView getUpdateUserFormInformation(@PathVariable String id, ModelAndView modelAndView) {
        User user = findUserService.findByUserId(id);
        UserResponseDto userResponseDto = UserMapper.convertEntityToDto(user);

        modelAndView.addObject("user", userResponseDto);
        modelAndView.setViewName("user/updateForm");

        return modelAndView;
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateUser(@PathVariable String id, UpdateUserRequestDto updateUserRequestDto, ModelAndView modelAndView) {
        boolean passwordMatch = findUserService.checkPassWordMatch(id, updateUserRequestDto.getPrePassword());

        if (passwordMatch == true) {
            UserVo updateUserVo = UserMapper.convertUpdateUserDtoToVo(id, updateUserRequestDto);
            updateUserService.updateInformation(updateUserVo);
        }

        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

}