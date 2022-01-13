package com.kakao.cafe.view;

import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

public class UserView {
    private static final Logger log = LoggerFactory.getLogger(UserView.class);
    public void getUsersView(Model model, List<UserListDto> userList) {
        model.addAttribute("userList", userList);
    }
}
