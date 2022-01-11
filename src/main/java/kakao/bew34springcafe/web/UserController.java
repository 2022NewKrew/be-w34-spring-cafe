package kakao.bew34springcafe.web;

import kakao.bew34springcafe.dto.UserJoinForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping("/user/form")
    public String form(Model model){
        return "user/form";
    }

    @PostMapping("/user/join")
    public String postJoin(UserJoinForm userJoinForm){
        logger.info(userJoinForm.toString());
        return "user/list";
    }


}
