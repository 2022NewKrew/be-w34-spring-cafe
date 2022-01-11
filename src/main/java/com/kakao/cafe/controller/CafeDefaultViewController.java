package com.kakao.cafe.controller;

import com.kakao.cafe.url.PostRedirect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CafeDefaultViewController {
    @GetMapping()
    String forwardDefaultUrl () {
        return PostRedirect.POST_REDIRECT_LIST;
    }
}
