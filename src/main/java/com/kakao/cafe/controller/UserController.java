package com.kakao.cafe.controller;

import com.kakao.cafe.constant.PageSize;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ModelAndView userList(@RequestParam(defaultValue = "1") Integer pageNum, ModelAndView mav) {
        int numOfUser = userService.countAll();
        mav.addObject("numOfUser", numOfUser);

        List<SimpleUserInfo> userInfos = userService.getListOfSimpleUserInfo(pageNum, PageSize.USER_LIST_SIZE);
        mav.addObject("userInfos", userInfos);

        mav.setViewName("userList");
        return mav;
    }
}
