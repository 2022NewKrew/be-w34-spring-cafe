package kakao.bew34springcafe.web;

import kakao.bew34springcafe.db.UserList;
import kakao.bew34springcafe.domain.User;
import kakao.bew34springcafe.dto.UserJoinForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping("/form")
    public String form(Model model){
        return "user/form";
    }

    @GetMapping("")
    public String showUserList(Model model){
        model.addAttribute("users", UserList.getUserList());
        return "user/list";
    }

    @PostMapping("/join")
    //public String postJoin(String uid, String umail, String upw){ //
    public String postJoin(UserJoinForm userJoinForm){
        logger.info("[info] user join:"+ userJoinForm);
        User user = new User(userJoinForm);
        UserList.addUser(user);
        return "redirect:/user";
    }


}
