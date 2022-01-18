package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.dto.UserDTO.Update;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.BindingException;
import com.kakao.cafe.error.exception.ForbiddenAccessException;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public String create(@ModelAttribute @Validated Create createDTO,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }

        userService.create(createDTO);

        return "redirect:/users";
    }

    @PutMapping
    public String update(@ModelAttribute @Validated Update updateDTO,
        HttpServletRequest request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }

        HttpSession session = request.getSession();
        AuthInfo authInfo = (AuthInfo) session.getAttribute("auth");
        if (authInfo == null) {
            throw new ForbiddenAccessException(ErrorCode.FORBIDDEN_ACCESS, "Edit User Profile");
        }

        userService.update(authInfo, updateDTO);
        return "redirect:/users";
    }

    @GetMapping
    public ModelAndView readAll(Map<String, Object> model) {
        List<Result> resultDTOs = userService.readAll();
        model.put("users", resultDTOs);

        return new ModelAndView("/user/list", model);
    }

    @GetMapping("/{uid}")
    public ModelAndView read(Map<String, Object> model,
        @PathVariable String uid) {
        Result resultDTO = userService.readByUid(uid);
        model.put("user", resultDTO);

        return new ModelAndView("/user/profile", model);
    }
}
