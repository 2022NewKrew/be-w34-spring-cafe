package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.*;
import com.kakao.cafe.user.exception.UserNotMatchedException;
import com.kakao.cafe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //회원가입 목록 확인
    @GetMapping(value = "/user/list")
    public String userPrintAll(Model model){
        List<User> users = userService.getAllUser();
        List<UserListDTO> usersListDTO = users.stream().map(UserListDTO::new).collect(Collectors.toList());
        model.addAttribute("users", usersListDTO); //userService에서 DTO를 만들어서 반환하는 것 보다 controller에서 바꾸는 것이 더 유연해보임. (추후 List<User> 타입의 로직을 처리해야할 수도 있을 것 같음)
        return "/user/list";
    }

    //회원가입요청
    @PostMapping(value = "/user/create")
    public String userCreate(UserCreateDTO userCreateDTO){
        userService.userCreate(userCreateDTO);
        return "redirect:/user/list";
    }

    //회원정보수정
    @PutMapping(value = "/user/update")
    public String userUpdate(UserCreateDTO userCreateDTO, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");
        if(!user.equalsUserId(userCreateDTO.getUserId())){
            throw new UserNotMatchedException("자신의 아이디만 수정이 가능합니다.");
        }

        userService.userUpdate(userCreateDTO);

        return "redirect:/user/list";
    }


    //회원개인프로필 확인
    @GetMapping(value = "/users/{userId}")
    public String userProfile(@PathVariable("userId") String userId, Model model){
        User user = userService.getUserByUserId(userId);
        UserProfileDTO userProfileDTO = new UserProfileDTO(user);
        model.addAttribute("name", userProfileDTO.getName());
        model.addAttribute("email", userProfileDTO.getEmail());
        return "/user/profile";
    }


    //회원개인정보 수정
    @GetMapping(value = "/users/{userId}/form")
    public String userUpdate(@PathVariable("userId") String userId, Model model, HttpSession session){
        User user = userService.getUserByUserId(userId);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(user);
        model.addAttribute("userid", userUpdateDTO.getUserId());
        model.addAttribute("name", userUpdateDTO.getName());
        model.addAttribute("email", userUpdateDTO.getEmail());
        return "/user/updateform";
    }


    //로그인요청
    @PostMapping(value = "/user/login/try")
    public String userLogin(UserLoginDTO userLoginDTO, HttpSession session){
        if(userService.isValidLogin(userLoginDTO)) {
            User user = userService.getUserByUserId(userLoginDTO.getUserId());
            session.setAttribute("sessionedUser", user);
        }

        return "redirect:/";
    }

    //로그아웃요청
    @GetMapping(value = "/user/logout")
    public String userLogout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }


}
