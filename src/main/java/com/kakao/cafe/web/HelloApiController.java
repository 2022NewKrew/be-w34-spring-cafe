package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {

    Logger logger = LoggerFactory.getLogger(HelloApiController.class);

    @GetMapping("/api/hello")
    public Fruit hello() {
        Fruit f = new Fruit("Apple", 50);
        logger.info(f.toString());
        return f;
    }
}
