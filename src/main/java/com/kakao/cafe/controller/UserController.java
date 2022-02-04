package com.kakao.cafe.controller;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleLoginForm;
import com.kakao.cafe.dto.SampleUserForm;
import com.kakao.cafe.dto.UserValidateAjaxForm;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String userList(Model model, HttpSession session){
        logger.info("userList print");
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            model.addAttribute("currentUser", user);
            logger.info("User session get {}", user);
        }
        model.addAttribute("users", userService.printUsers());
        return "user/userListPage";
    }

    @GetMapping("/login")
    public String userLoginPage(){
        logger.info("userLoginPage print");
        return "user/userLoginForm";
    }

    @GetMapping("/{numID}")
    public String userProfile(Model model, @PathVariable Long numID, HttpSession session){
        logger.info("userprofile print numID : {}", numID);

        Object value = session.getAttribute("user");
        if (value != null && ((User) value).getId().equals(numID)) {
            model.addAttribute("currentUser", (User) value);
        }

        model.addAttribute("userprofile", userService.findUser(numID));
        return "user/userPage";
    }

    @PostMapping("/{numID}/validate.do")
    @ResponseBody
    public String checkPW4Update(UserValidateAjaxForm form, @PathVariable Long numID){
        logger.info("check password for updateProfile {} {}", numID, form.getPassword());
        if (userService.checkPassword(numID, form.getPassword())){
            return "true";
        }
        return "false";
    }

    @GetMapping("/{numID}/update")
    public String updateProfile(Model model, @PathVariable Long numID){
        logger.info("updateProfile print userID : {}", numID);
        model.addAttribute("userprofile", userService.findUser(numID));

        return "user/userUpdateForm";
    }

    @GetMapping("/{numID}/logout")
    public String logoutUser(Model model, @PathVariable Long numID, HttpSession session){
        logger.info("logoutUser print userID : {}", numID);

        Object value = session.getAttribute("user");
        if (value != null ) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signUp(){
        logger.info("signUp page");
        return "user/userForm";
    }

    @PostMapping("/create")
    @ResponseBody
    public String userCreate(SampleUserForm form){
        logger.info("userCreate print {}" ,form.toString());
        if (userService.addUser(form)){
            return "<script>alert('Create Success');location.href='/'</script>";
        }
        return "<script>alert('ID already exists');location.href='/user/signup'</script>";
    }

    @PutMapping("/update")
    @ResponseBody
    public String userUpdate(SampleUserForm form){
        logger.info("userUpdate print {}" ,form.toString());
        if (userService.updateUser(form)){
            return "<script>alert('Update Success');location.href='/user'</script>";
        }
        return "<script>alert('Update failed');location.href='/user'</script>";
    }

    @PostMapping("/login")
    @ResponseBody
    public String userLogin(SampleLoginForm form, HttpSession session){
        logger.info("userLogin print {}" ,form.toString());
        User loginUser = userService.loginUser(form);
        if (loginUser != null){
            session.setAttribute("user", loginUser);
            return "<script>alert('Login Success');location.href='/'</script>";
        }
        return "<script>alert('Login failed : Not exist ID or Invalid Password');location.href='/user/login'</script>";
    }

    @ExceptionHandler(CustomException.class)
    public Object exceptionHandle(Model model, CustomException e){
        logger.error("Error exception, {}", e.getErrorCode());
        model.addAttribute("ErrorCode", e.getErrorCode().getHttpStatus());
        model.addAttribute("ErrorMessage", e.getErrorCode().getDetail());
        return "error";
    }


}
