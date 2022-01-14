package com.kakao.cafe.web;

import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {

    Logger logger = LoggerFactory.getLogger(HelloApiController.class);

    @GetMapping("/api/hello")
    public User hello() {
        User user = new User("jake", "1234", "jake", "jake@kakao.com");
        logger.info(user.toString());
        return user;
    }

}
