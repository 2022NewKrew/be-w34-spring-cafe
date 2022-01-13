package com.kakao.cafe.repository;

import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    Map<String, UserDao> userMap;
    long index;

    public UserRepository() {
        userMap = new HashMap<>();
        index = 0;
        this.insert(new User("kakaopasta", "ppasta", "lasagna", "kakaopasta@daum.net"));
        this.insert(new User("chicken123", "tiba2chicken", "Kim chicken", "chicken@gmail.com"));
        this.insert(new User("pizza82", "pizzapizza", "Mr.Pizza", "pizza@gmail.com"));
    }

    private UserDao toDao(User user) {
        return new UserDao(index++, user.getId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void insert(User user) {
        log.info("UserRepository insert");
        log.info(user.toString());
        log.info(user.getId());
        userMap.put(user.getId(), toDao(user));
    }

    public List<UserDao> selectAll() {
        return new ArrayList<>(userMap.values());
    }
}
