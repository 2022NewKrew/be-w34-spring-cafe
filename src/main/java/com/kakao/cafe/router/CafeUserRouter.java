package com.kakao.cafe.router;


import com.kakao.cafe.url.UsersViewURL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class CafeUserRouter {
    @PostMapping("/create")
    String signIn(){ // 회원가입
        return UsersViewURL.USER_SIGN_IN.getMappingUrl();
    }

    @GetMapping()
    String getUserList () { // 유저 목록
        return UsersViewURL.USER_GET_LIST_VIEW.getMappingUrl();
    }

    @GetMapping("/:userId")
    String getUserProfile (@PathVariable("userId") String userId) { // 유저 프로필
        return UsersViewURL.USER_GET_PROFILE_VIEW.getMappingUrl();
    }
}
