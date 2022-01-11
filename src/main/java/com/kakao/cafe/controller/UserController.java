package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDTO.Create;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(
        UserController.class.getSimpleName());
    private final UserService userService;

    @PostMapping
    public String create(@ModelAttribute @Validated Create createDTO,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                .forEach(fieldError -> logger.error("Caused Field : {}, Message : {}",
                    fieldError.getField(),
                    fieldError.getDefaultMessage()));
            return "redirect:/users/form-failed";
        }

        try {
            userService.create(createDTO);
        } catch (ResponseStatusException e) {
            return "redirect:/users/form-failed";
        }

        return "redirect:/users";
    }

    @GetMapping
    public ModelAndView readAll(Map<String, Object> model) {
        List<Result> resultDTOs = userService.readAll();
        model.put("users", resultDTOs);

        return new ModelAndView("user/list", model);
    }

    @GetMapping("/{userId}")
    public ModelAndView read(Map<String, Object> model,
        @PathVariable String userId) {
        Result resultDTO = userService.readByUserId(userId);
        model.put("user", resultDTO);

        return new ModelAndView("user/profile", model);
    }
}
