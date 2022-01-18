package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DbRepository {

    Logger logger = LoggerFactory.getLogger(DbRepository.class);
    private Map<String, User> users = new ConcurrentHashMap<>();

    public User saveUser(User user) {
        logger.info("save user: {}", user);
        return users.put(user.getEmail(), user);
    }

    public User findUserByEmail(String email) {
        return users.get(email);
    }
}
