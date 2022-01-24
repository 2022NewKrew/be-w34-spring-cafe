package com.kakao.cafe.controller;



import com.kakao.cafe.dto.SampleUserForm;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String userList(Model model){
        logger.info("userList print");
        model.addAttribute("users", userService.printUsers());
        return "user/userListPage";
    }

    @GetMapping("/{numID}")
    public String userProfile(Model model, @PathVariable Long numID){
        logger.info("userprofile print numID : {}", numID);
        model.addAttribute("userprofile", userService.findUser(numID));

        return "user/userPage";
    }

    @GetMapping("/{numID}/update")
    public String updateProfile(Model model, @PathVariable Long numID){
        logger.info("updateProfile print userID : {}", numID);
        model.addAttribute("userprofile", userService.findUser(numID));

        return "user/userUpdateForm";
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
            return "<script>alert('Create Success');location.href='/user'</script>";
        }
        return "<script>alert('ID already exists');location.href='/user/signup'</script>";
    }

    @PostMapping("/update")
    @ResponseBody
    public String userUpdate(SampleUserForm form){
        logger.info("userUpdate print {}" ,form.toString());
        if (userService.updateUser(form)){
            return "<script>alert('Update Success');location.href='/user'</script>";
        }
        return "<script>alert('Invalid Password');location.href='/user'</script>";
    }

}
