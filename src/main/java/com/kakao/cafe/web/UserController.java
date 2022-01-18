package com.kakao.cafe.web;



import com.kakao.cafe.dto.SampleUserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private Users users = new Users();

    @GetMapping("/")
    public String index(){
        logger.info("index");
        return "index";
    }

    @GetMapping("/users")
    public String userList(Model model){
        logger.info("users print");
        model.addAttribute("users", users.getUserList());
        return "userList";
    }

    @GetMapping("/users/{userID}")
    public String userProfile(Model model, @PathVariable String userID){
        logger.info("userprofile print userID : {}", userID);
        model.addAttribute("userprofile", users.findUser(userID));

        return "profile";
    }

    @GetMapping("/users/{userID}/update")
    public String updateProfile(Model model, @PathVariable String userID){
        logger.info("updateProfile print userID : {}", userID);
        model.addAttribute("userprofile", users.findUser(userID));

        return "updateForm";
    }

    @GetMapping("/user/signup")
    public String signUp(){
        logger.info("signUp page");
        return "signUp";
    }

    @PostMapping("/user/create")
    public String userCreate(SampleUserForm form){
        logger.info("userCreate print {}" ,form.toString());
        users.addUser(form);
        return "redirect:/users";
    }

    @PostMapping("/user/update")
    @ResponseBody
    public String userUpdate(SampleUserForm form){
        logger.info("userUpdate print {}" ,form.toString());
        if (users.updateUser(form)){
            return "<script>alert('success');location.href='/users'</script>";
        };
        return "<script>alert('failed');location.href='/users'</script>";
    }

}
