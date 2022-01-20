package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.application.user.UpdateUserService;
import com.kakao.cafe.application.user.validation.DuplicatedUserIdException;
import com.kakao.cafe.application.user.validation.IllegalPasswordException;
import com.kakao.cafe.application.user.validation.NonExistsUserIdException;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;
import com.kakao.cafe.interfaces.user.dto.UserMapper;
import com.kakao.cafe.interfaces.user.dto.request.JoinUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.request.UpdateUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.response.UserResponseDto;
import com.kakao.cafe.interfaces.user.exception.UnauthorizedUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public String joinUser(@Valid JoinUserRequestDto joinUserRequestDto) throws DuplicatedUserIdException {
        UserVo user = UserMapper.convertJoinUserDtoToVo(joinUserRequestDto);

            signUpUserService.join(user);

        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId(@PathVariable String userId, Model model) throws NonExistsUserIdException {
        User user = findUserService.findByUserId(userId);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());

        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public ModelAndView getUpdateUserFormInformation(@PathVariable String id, HttpServletRequest request, ModelAndView modelAndView) throws UnauthorizedUserException {
        User user = (User) request.getAttribute("user");

        if (!user.getUserId().equals(id)) {
            throw new UnauthorizedUserException();
        }

        UserResponseDto userResponseDto = UserMapper.convertEntityToDto(user);
        modelAndView.addObject("user", userResponseDto);
        modelAndView.setViewName("user/updateForm");

        return modelAndView;
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateUser(@PathVariable String id, @Valid UpdateUserRequestDto updateUserRequestDto, HttpServletRequest request, ModelAndView modelAndView) throws UnauthorizedUserException, NonExistsUserIdException {
        User user = (User) request.getAttribute("user");

        if (!user.getUserId().equals(id)) {
            throw new UnauthorizedUserException();
        }

        boolean passwordMatch = findUserService.checkPassWordMatch(id, updateUserRequestDto.getPrePassword());

        if (!passwordMatch) {
            throw new IllegalPasswordException();
        }

        UserVo updateUserVo = UserMapper.convertUpdateUserDtoToVo(id, updateUserRequestDto);
        updateUserService.updateInformation(updateUserVo);

        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

}