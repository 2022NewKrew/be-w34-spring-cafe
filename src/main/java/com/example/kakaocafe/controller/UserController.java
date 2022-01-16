package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import com.example.kakaocafe.domain.user.SignUpService;
import com.example.kakaocafe.domain.user.UserDAO;
import com.example.kakaocafe.domain.user.dto.SignUpForm;
import com.example.kakaocafe.domain.user.dto.UpdateUserForm;
import com.example.kakaocafe.domain.user.dto.UserProfile;
import com.example.kakaocafe.domain.user.dto.UserProfileOfTableRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final SignUpService signUpService;
    private final UserDAO userDAO;

    @PostMapping
    public ModelAndView create(@ModelAttribute SignUpForm signUpForm, RedirectAttributes redirectAttr) {
        signUpService.signUp(signUpForm);

        final UserProfile userProfile = new UserProfile(signUpForm.getEmail(), signUpForm.getName());

        redirectAttr.addFlashAttribute("email", userProfile.getEmail());
        redirectAttr.addFlashAttribute("name", userProfile.getName());

        return new ModelAndView(URLPath.SHOW_SIGN_UP_SUCCESS.getRedirectPath());
    }

    @PutMapping(path = "/{id}")
    public ModelAndView update(UpdateUserForm updateUserForm) {

        userDAO.update(updateUserForm);

        return new ModelAndView(URLPath.SHOW_USER_LIST.getRedirectPath());
    }

    @GetMapping
    public ModelAndView getAll() {
        final List<UserProfileOfTableRow> userProfileOfTableRows = userDAO.getAllUserProfileOfTableRow();

        return new ModelAndView(ViewPath.SHOW_USER_LIST)
                .addObject("users", userProfileOfTableRows);
    }

    @GetMapping(path = "/updateForm")
    public ModelAndView show_updateForm(@SessionAttribute("userKey") long userKey) {
        final UserProfile userProfile = userDAO.getUserProfileById(userKey)
                .orElseThrow();

        return new ModelAndView(ViewPath.UPDATE_USER_FROM)
                .addObject("user", userProfile);
    }
}
