package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/hello")
    public String hello() {
        logger.info("server test success");
        return "server test page";
    }

}
